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


OrderItemFormSet = forms.inlineformset_factory(
    Order, OrderItem,
    form=forms.ModelForm,
    fields=['spare', 'count'],
    extra=1,
    can_delete=True
)


class DateRangeForm(forms.Form):
    start_date = forms.DateField(widget=forms.TextInput(attrs={'type': 'date'}), label='Начальная дата')
    end_date = forms.DateField(widget=forms.TextInput(attrs={'type': 'date'}), label='Конечная дата')