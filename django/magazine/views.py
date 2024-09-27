from django.shortcuts import render, redirect, get_object_or_404

from magazine.forms import SpareForm, CarForm, OrderForm, OrderItemForm
from magazine.models import Spare, Car, Order, OrderItem


def home(request):
    return redirect('car_list')


def spare_list(request, car_id):
    car = Car.objects.get(pk=car_id)
    spare_list = Spare.objects.filter(car=car)
    return render(request, 'spare_list.html', {'car': car,'spare_list': spare_list})


def car_list(request):
    car_list = Car.objects.all()
    return render(request, 'car_list.html', {'car_list': car_list})


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


def order_management(request, order_id=None):
    order = get_object_or_404(Order, id=order_id) if order_id else None

    if request.method == 'POST':
        if order:
            order_form = OrderForm(request.POST, instance=order)
            if order_form.is_valid():
                order_form.save()

                # Обработка элементов заказа
                for item_form in request.POST.getlist('order_items'):
                    # Здесь вы можете добавить логику для обновления или добавления элементов
                    # Например, вы можете использовать JSON для передачи данных о товарах
                    pass
                return redirect('order_list')
        else:
            order_form = OrderForm(request.POST)
            if order_form.is_valid():
                order = order_form.save()
                return redirect('order_list')
    else:
        order_form = OrderForm(instance=order) if order else OrderForm()

    order_items = OrderItem.objects.filter(order=order) if order else []
    order_item_forms = [OrderItemForm(instance=item) for item in order_items]

    return render(request, 'order.html', {
        'order_form': order_form,
        'order_items': order_item_forms,
        'order': order
    })