from django.shortcuts import render, redirect, get_object_or_404

from magazine.forms import SpareForm, CarForm, OrderForm, OrderItemFormSet
from magazine.models import Spare, Car, Order, OrderItem


def home(request):
    return render(request, 'home.html')


def spare_list(request, car_id):
    car = Car.objects.get(pk=car_id)
    spare_list = Spare.objects.filter(car=car)
    return render(request, 'spare_list.html', {'car': car,'spare_list': spare_list})


def car_list(request):
    car_list_ = Car.objects.all()
    return render(request, 'car_list.html', {'car_list': car_list_})


def spare_management(request, spare_id=None):
    if spare_id:
        spare = get_object_or_404(Spare, id=spare_id)

        if request.method == 'POST':
            if 'delete' in request.POST:
                spare.delete()
                return redirect('car_list')
            else:
                form = SpareForm(request.POST, instance=spare)
                if form.is_valid():
                    form.save()
                    return redirect('car_list')
        else:
            form = SpareForm(instance=spare)
    else:
        if request.method == 'POST':
            form = SpareForm(request.POST)
            if form.is_valid():
                form.save()
                return redirect('car_list')
        else:
            form = SpareForm()

    return render(request, 'spare.html', {'form': form, 'spare': spare if spare_id else None})


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
    order_list_ = Order.objects.all()
    return render(request, 'order_list.html', {'order_list': order_list_, 'qqq': len(order_list_)})


def order_management(request, order_id=None):
    order = get_object_or_404(Order, id=order_id) if order_id else None

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