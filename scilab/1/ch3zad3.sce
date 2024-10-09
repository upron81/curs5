clf;
subplot(2, 2, 1);
plot(x, y);
title("График функции f1");
xgrid();

subplot(2, 2, 2);
plot(x, y1, "-r", x, y2, "-b");
title("Графики y1 и y2");
legend("y1", "y2");
xgrid();

subplot(2, 2, 3);
plot(x, sin(x), "-g");
title("График sin(x)");
xgrid();

subplot(2, 2, 4);
plot(x, cos(x), "-m");
title("График cos(x)");
xgrid();
