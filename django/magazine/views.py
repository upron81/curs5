from django.http import HttpResponse
from django.shortcuts import render

from magazine.models import Spare


def index(request):
    spare_list = Spare.objects.all()
    return render(request, 'sparelist.html', {'spare_list': spare_list})

