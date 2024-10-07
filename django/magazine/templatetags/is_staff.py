from django import template

from djangoapp.settings import STAFF_GROUP_NAME

register = template.Library()

@register.filter
def is_staff(user):
    return user.groups.filter(name=STAFF_GROUP_NAME).exists()