from django import forms
from .models import Spare, Car


class SpareForm(forms.ModelForm):
    class Meta:
        model = Spare
        fields = '__all__'


class CarForm(forms.ModelForm):
    class Meta:
        model = Car
        fields = '__all__'