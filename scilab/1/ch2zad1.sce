A = [2 3 2; 1 3 -1; 4 1 3];
B = [3 3 1; 0 6 2; 1 9 2];

V1 = A(2, :);
V2 = B(:, 3);
V3 = A(:, 2);

// 1.2
V1_dot_V2 = V1 * V2;
A_mul_V2 = A * V2;

// 1.3
A_mul_B = A * B;
A_inv = inv(A);
A_inv_A = A_inv * A;
A_T = A'; 
B_T = B';

// 1.4
det_A = det(A);
det_B = det(B);

// 1.5
V3_elem_V2 = V3 .* V2;
A_elem_B = A .* B;


disp("V1 * V2: "), disp(V1_dot_V2);
disp("A * V2: "), disp(A_mul_V2);
disp("A * B: "), disp(A_mul_B);
disp("A^(-1): "), disp(A_inv);
disp("A^(-1) * A: "), disp(A_inv_A);
disp("A^T: "), disp(A_T);
disp("B^T: "), disp(B_T);
disp("det(A): "), disp(det_A);
disp("det(B): "), disp(det_B);
disp("V3 .* V2: "), disp(V3_elem_V2);
disp("A .* B: "), disp(A_elem_B);
