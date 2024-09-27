from django import forms
from .models import Spare, Car, Order, OrderItem


class SpareForm(forms.ModelForm):
    class Meta:
        model = Spare
        fields = '__all__'


class CarForm(forms.ModelForm):
    class Meta:
        model = Car
        fields = '__all__'


class OrderForm(forms.ModelForm):
    class Meta:
        model = Order
        fields = '__all__'


class OrderItemForm(forms.ModelForm):
    class Meta:
        model = OrderItem
        fields = '__all__'
