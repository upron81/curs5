from django.db import models

class Spare(models.Model):
    name = models.CharField('название', max_length=100)
    price = models.IntegerField('цена')

    def __str__(self):
        return str(self.name)