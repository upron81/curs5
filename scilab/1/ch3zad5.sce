x = linspace(-2*%pi, 2*%pi, 50);
y = linspace(-2*%pi, 2*%pi, 50);
[X, Y] = ndgrid(x, y);

Z = (1 + sin(X) ./ X) .* (sin(Y) ./ Y);

clf;

subplot(2, 2, 1);
plot3d(x, y, Z);
title('plot3d график');
xtitle('X', 'Y', 'Z');
xgrid();

subplot(2, 2, 2);
mesh(X, Y, Z);
title('mesh график');
xtitle('X', 'Y', 'Z');
xgrid();

subplot(2, 2, 3);
contour(x, y, Z, 10);
title('contour (замена meshc)');
xtitle('X', 'Y', 'Z');
xgrid();

subplot(2, 2, 4);
surf(X, Y, Z);
title('surf график');
xtitle('X', 'Y', 'Z');
xgrid();
