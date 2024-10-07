from django.contrib.auth.decorators import user_passes_test, login_required
from django.shortcuts import render, redirect, get_object_or_404
from django.db.models import F, Sum
from django.utils.decorators import method_decorator
from django.views.generic import TemplateView
from django.views.generic.edit import FormMixin
from django.urls import reverse_lazy

from magazine.templatetags.is_staff import is_staff
from magazine.forms import SpareForm, CarForm, OrderForm, OrderItemFormSet, DateRangeForm
from magazine.models import Spare, Car, Order, OrderItem


class HomeView(TemplateView):
    template_name = 'home.html'


def spare_list(request, car_id):
    car = Car.objects.get(pk=car_id)
    spare_list = Spare.objects.filter(car=car)
    return render(request, 'spare_list.html', {'car': car,'spare_list': spare_list})


def car_list(request):
    car_list_ = Car.objects.all()
    return render(request, 'car_list.html', {'car_list': car_list_})


@method_decorator(user_passes_test(is_staff), name='dispatch')
class SpareManagementView(FormMixin, TemplateView):
    model = Spare
    template_name = 'spare.html'
    form_class = SpareForm
    success_url = reverse_lazy('car_list')

    def get_object(self):
        spare_id = self.kwargs.get('spare_id')
        if spare_id:
            return get_object_or_404(Spare, id=spare_id)
        return None

    def get_context_data(self, **kwargs):
        context = super().get_context_data(**kwargs)
        context['spare'] = self.get_object()

        if context['spare']:
            context['form'] = self.form_class(instance=context['spare'])
        else:
            context['form'] = self.form_class()

        return context

    def post(self, request, *args, **kwargs):
        spare = self.get_object()

        if spare:
            form = self.form_class(request.POST, request.FILES, instance=spare)
        else:
            form = self.form_class(request.POST, request.FILES)

        if 'delete' in request.POST and spare:
            spare.delete()
            return redirect(self.success_url)

        if form.is_valid():
            form.save()
            return redirect(self.success_url)

        return self.form_invalid(form)


@user_passes_test(is_staff)
def car_management(request, car_id=None):
    if car_id:
        car = get_object_or_404(Car, id=car_id)

        if request.method == 'POST':
            if 'delete' in request.POST:
                car.delete()
                return redirect('car_list')
            else:
                form = CarForm(request.POST, instance=car)
                if form.is_valid():
                    form.save()
                    return redirect('car_list')
        else:
            form = CarForm(instance=car)
    else:
        if request.method == 'POST':
            form = CarForm(request.POST)
            if form.is_valid():
                form.save()
                return redirect('car_list')
        else:
            form = CarForm()

    return render(request, 'car.html', {'form': form, 'car': car if car_id else None})


def order_list(request):
    if request.user.is_authenticated:
        order_list_ = Order.objects.filter(user=request.user)
    else:
        order_list_ = Order.objects.none()

    return render(request, 'order_list.html', {'order_list': order_list_, 'qqq': len(order_list_)})


def order_management(request, order_id=None):
    order = get_object_or_404(Order, id=order_id, user=request.user) if order_id else None

    if request.method == 'POST':
        if order_id:
            order_form = OrderForm(request.POST, instance=order)
            order_item_formset = OrderItemFormSet(request.POST, instance=order)
        else:
            order_form = OrderForm(request.POST)
            order_item_formset = OrderItemFormSet(request.POST)

        if 'delete' in request.POST:
            if order:
                order.delete()
            return redirect('order_list')

        if order_form.is_valid() and order_item_formset.is_valid():
            order = order_form.save()
            order_item_formset.instance = order
            order_item_formset.save()
            return redirect('order_list')

    else:
        if order_id:
            order_form = OrderForm(instance=order)
            order_item_formset = OrderItemFormSet(instance=order)
        else:
            order_form = OrderForm()
            order_item_formset = OrderItemFormSet()

    return render(request, 'order.html', {
        'order_form': order_form,
        'order_item_formset': order_item_formset,
        'order': order
    })


@user_passes_test(is_staff)
def completed_orders_sum(request):
    total_sum = None
    form = DateRangeForm(request.POST or None)

    if request.method == 'POST' and form.is_valid():
        start_date = form.cleaned_data['start_date']
        end_date = form.cleaned_data['end_date']

        total_sum = OrderItem.objects.filter(
            order__completed=True,
            order__date__range=(start_date, end_date)
        ).aggregate(total=Sum(F('count') * F('spare__price')))['total']

        if total_sum is None:
            total_sum = 0

    return render(request, 'completed_orders_sum.html', {
        'form': form,
        'total_sum': total_sum
    })


@login_required
def add_to_cart(request, spare_id):
    count = request.GET.get('count')

    if count is None or int(count) <= 0:
        return redirect('home')

    count = int(count)
    order, created = Order.objects.get_or_create(user=request.user, is_cart=True)
    spare = get_object_or_404(Spare, id=spare_id)
    order_item, created = OrderItem.objects.get_or_create(order=order, spare=spare, defaults={'count': 0})
    order_item.count += count
    order_item.save()

    return redirect('home')


def order_approve(request, order_id):
    order = get_object_or_404(Order, id=order_id, user=request.user)
    order.is_cart = False
    order.save()
    return redirect('order_list')
