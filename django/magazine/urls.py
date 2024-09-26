from django.urls import path

from . import views

urlpatterns = [
    path('car/<int:car_id>/', views.spare_list, name='spare_list'),
    path('spare/add/', views.add_spare, name='add_spare'),
    path('car/add/', views.add_car, name='add_car'),
    path("", views.car_list, name="car_list"),
]