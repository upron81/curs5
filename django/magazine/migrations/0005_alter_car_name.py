# Generated by Django 5.1.1 on 2024-09-26 08:42

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('magazine', '0004_alter_spare_car'),
    ]

    operations = [
        migrations.AlterField(
            model_name='car',
            name='name',
            field=models.CharField(max_length=100, unique=True, verbose_name='название'),
        ),
    ]