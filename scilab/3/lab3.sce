X = [5, 6, 7, 8];
Y = [5, 4.5, 3, 3];
X_star = 6.5;

n = length(X);
sumX = sum(X);
sumY = sum(Y);
sumXY = sum(X .* Y);
sumX2 = sum(X .^ 2);

a1_linear = (n * sumXY - sumX * sumY) / (n * sumX2 - sumX^2);
a0_linear = (sumY - a1_linear * sumX) / n;

// Коэффициент детерминации R^2 для линейной регрессии
Y_reg_linear = a0_linear + a1_linear * X;
R2_linear = 1 - sum((Y - Y_reg_linear).^2) / sum((Y - mean(Y)).^2);

Y_star_linear = a0_linear + a1_linear * X_star;

X2 = X.^2;
A = [sum(X2), sum(X), n;
     sum(X .* X2), sum(X2), sum(X);
     sum(X.^3), sum(X2), n];
B = [sum(Y); sum(X .* Y); sum(X2 .* Y)];
coeffs = A \ B;

a2_parabola = coeffs(1);
a1_parabola = coeffs(2);
a0_parabola = coeffs(3);

// Коэффициент детерминации R^2 для параболической регрессии
Y_reg_parabola = a2_parabola * X.^2 + a1_parabola * X + a0_parabola;
R2_parabola = 1 - sum((Y - Y_reg_parabola).^2) / sum((Y - mean(Y)).^2);

Y_star_parabola = a2_parabola * X_star^2 + a1_parabola * X_star + a0_parabola;


clf;
X_fit = linspace(min(X), max(X), 100);
Y_fit_linear = a0_linear + a1_linear * X_fit;
Y_fit_parabola = a2_parabola * X_fit.^2 + a1_parabola * X_fit + a0_parabola;

plot(X, Y, 'o', X_fit, Y_fit_linear, '-', X_fit, Y_fit_parabola, '--');
xlabel('X'); ylabel('Y');
title('Сравнение линейной и параболической регрессий');
legend(['Данные', 'Линейная регрессия', 'Параболическая регрессия'], 'location', 'lower right');

disp("Линейная регрессия R^2: " + string(R2_linear));
disp("Параболическая регрессия R^2: " + string(R2_parabola));

if R2_parabola > R2_linear then
    disp("Параболическая регрессия более точная");
else
    disp("Линейная регрессия более точная");
end

disp("Прогноз для X* = " + string(X_star) + ":");
disp("- Линейная модель Y* = " + string(Y_star_linear));
disp("- Параболическая модель Y* = " + string(Y_star_parabola));
