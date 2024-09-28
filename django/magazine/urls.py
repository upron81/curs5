from django.urls import path

from . import views

urlpatterns = [
    path("carlist/", views.car_list, name="car_list"),
    path('sparelist/<int:car_id>/', views.spare_list, name='spare_list'),
    path('spare/<int:spare_id>/', views.SpareManagementView.as_view(), name='spare_management'),
    path('spare/', views.SpareManagementView.as_view(), name='spare_add'),
    path('car/<int:car_id>/', views.car_management, name='car_management'),
    path('car/', views.car_management, name='car_add'),
    path('orderlist/',  views.order_list, name='order_list'),
    path('order/<int:order_id>/',  views.order_management, name='order_management'),
    path('order/',  views.order_management, name='order_add'),
    path('completed_orders_sum/', views.completed_orders_sum, name='completed_orders_sum'),
    path('', views.HomeView.as_view(), name='home')
]