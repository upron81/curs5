a = -5;
b = 5;
h = 1;

x = a:h:b;

y = 3 * sin(x);
z = 0.015 * x.^3;

disp(y);
disp(z);
