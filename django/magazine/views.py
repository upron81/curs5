from django.http import HttpResponse
from django.shortcuts import render, redirect

from magazine.forms import SpareForm, CarForm
from magazine.models import Spare, Car


def spare_list(request, car_id):
    car = Car.objects.get(pk=car_id)
    spare_list = Spare.objects.filter(car=car)
    return render(request, 'spare_list.html', {'car_name': car.name,'spare_list': spare_list})


def car_list(request):
    car_list = Car.objects.all()
    return render(request, 'car_list.html', {'car_list': car_list})


def add_spare(request):
    if request.method == 'POST':
        form = SpareForm(request.POST)
        if form.is_valid():
            new_spare: Spare = form.save()
            return redirect('spare_list', car_id=new_spare.car.id)
    else:
        form = SpareForm()

    return render(request, 'add_spare.html', {'form': form})


def add_car(request):
    if request.method == 'POST':
        form = CarForm(request.POST)
        if form.is_valid():
            form.save()
            return redirect('car_list')
    else:
        form = CarForm()

    return render(request, 'add_car.html', {'form': form})