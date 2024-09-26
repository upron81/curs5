from django.db import models


class Car(models.Model):
    name = models.CharField('название', max_length=100, unique=True)

    def __str__(self):
        return str(self.name)


class Spare(models.Model):
    car = models.ForeignKey(Car, on_delete=models.CASCADE)
    name = models.CharField('название', max_length=100)
    price = models.IntegerField('цена')

    def __str__(self):
        return str(self.name)
