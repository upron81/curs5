from django.contrib import admin

from magazine.models import Spare, Car, Order, OrderItem

admin.site.register(Spare)
admin.site.register(Car)
admin.site.register(Order)
admin.site.register(OrderItem)
