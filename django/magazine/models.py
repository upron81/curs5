from django.db import models
from django.contrib.auth.models import User


class Car(models.Model):
    name = models.CharField('название', max_length=100, unique=True)

    def __str__(self):
        return f"{self.name}"


class Spare(models.Model):
    car = models.ForeignKey(Car, on_delete=models.CASCADE, verbose_name='машина')
    name = models.CharField('название', max_length=100)
    price = models.PositiveIntegerField('цена')

    def __str__(self):
        return f"{self.name}"


class Order(models.Model):
    user = models.ForeignKey(User, on_delete=models.CASCADE, verbose_name='клиент')
    date = models.DateTimeField('дата заказа', auto_now_add=True)
    completed = models.BooleanField('выполнен', default=False)
    is_cart = models.BooleanField('корзина', default=True)

    def __str__(self):
        return f"{self.user}, {self.date}"


class OrderItem(models.Model):
    order = models.ForeignKey(Order, on_delete=models.CASCADE, verbose_name='заказ')
    spare = models.ForeignKey(Spare, on_delete=models.CASCADE, verbose_name='деталь')
    count = models.PositiveIntegerField('количество')

    class Meta:
        constraints = [
            models.UniqueConstraint(fields=['order', 'spare'], name='unique_order_spare')
        ]

    def __str__(self):
        return f"{self.order}, {self.spare}, {self.count}"
