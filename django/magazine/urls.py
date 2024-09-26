from django.urls import path

from . import views

urlpatterns = [
    path('/car/<int:car_id>', views.sparelist, name='sparelist'),
    path("", views.carlist, name="carlist"),
]