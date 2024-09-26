from django.http import HttpResponse
from django.shortcuts import render

from magazine.models import Spare, Car


def sparelist(request, car_id):
    car = Car.objects.get(pk=car_id)
    spare_list = Spare.objects.filter(car=car)
    return render(request, 'sparelist.html', {'car_name': car.name,'spare_list': spare_list})


def carlist(request):
    car_list = Car.objects.all()
    return render(request, 'carlist.html', {'car_list': car_list})

