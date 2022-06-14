INSERT INTO nationality(country_code, country)
VALUES
('US', 'United States'),
('VN', 'Vietnam'),
('CA', 'Canada'),
('UK', 'United Kingdom')


INSERT INTO teacher(	employee_code, first_name, middle_name, last_name, 
						date_of_birth, phone_number, address, private_email,
						school_email, teacher_type, gender, degree, nationality)
VALUES 
('ANH.N6', 'Anh', 'Ngoc Truc', 'Nguyen', '1994-06-06', '0903553257', '1A Avenue Binh Tan district', 'anh.nguyen@gmail.com', 'anh.n6@gmail.com', 'VIETNAMESE', 'FEMALE', 'Bachelor of Art - English Teaching', 2),
('KHANH.T2', 'Khanh', 'Ngoc', 'Tran', '1997/04/30', '0815488295', '132/2 Nguyen Trai Street, D1', 'ngockhanh23@gmail.com', 'khanh.t2@gmail.com', 'VIETNAMESE', 'FEMALE', 'Bachelor of Art - Language Teaching', 2),
('GARLEN.S1', 'Garlen', 'John', 'Smith', '1990/06/14', '0905443867', 'Moonlight Apartment', 'garlen234@gmail.com', 'garlen.s1@gmail.com', 'EXPATRIATE', 'MALE', 'Bachelor of Art', 1),
('JOANNA.P3', 'Joanna', 'Lina', 'Potts', '1990/07/18', '0903451123', 'Moonlight Apartment', 'joanna.teaching@gmail.com', 'joanna.p3@gmail.com', 'EXPATRIATE', 'FEMALE', 'Bachelor of Art', 4)

INSERT INTO certification(name)
VALUES
('TESOL'),
('TEFL'),
('TESL'),
('CELTA'),
('DELTA'),
('IELTS'),
('TOEFL')

INSERT INTO certification_detail(score, issued_date, expired_date, description, teacher_id, certification_id)
VALUES
(9.0, '2020-05-06', '2023-05-06', 'Rank: average', 2,1),
(8.0, '2021-04-05', '2023-04-05', 'Listening: 8.0, Reading: 8.0, Speaking 8.0, Writing 8.0', 2, 6),
(8.5, '2021-12-04', '2023-12-04', 'Rank: average', 1,1),
(7.5, '2021-12-21', '2023-12-21', 'Rank: average', 3,1),
(7.5, '2021-05-23', '2023-05-23', 'Rank: average', 4,2)


INSERT INTO contract(	contract_id, startDate, endDate, payRate, bank_account,
						bank, branch, account_name, description, is_signed, teacher_id)
VALUES
('VN1102A', '2021-12-04', '2022-12-04', 180000, '10002406931', 'Vietcombaank', 'HCM', 'Nguyen Ngoc Truc Anh', '1 year contract for kid and young learner classes', true, 1),
('VN1103A', '2022-02-04', '2023-02-04', 180000, '10005394572', 'Vietcombank', 'HCM', 'Tran Ngoc Khanh', '1 year contract for kid and young learn classes', true, 2),
('VN1103B', '2022-02-04', '2023-02-04', 300000, '10005394572', 'Vietcombank', 'HCM', 'Tran Ngoc Khanh', '1 year contract for IELTS classes', true, 2),
('FR1201A', '2022-03-12', '2023-03-12', 500000, '10005739503', 'Vietcombank', 'HCM', 'Garlen John Smith', '1 year contract for kid and young learn, adult, ielts classes', true, 3),
('FR1202A', '2022-04-17', '2023-04-17', 480000, '10005403856', 'Vietcombank', 'HCM', 'Joanna Lina Potts', '1 year contract for kid and young learn, adult, ielts classes', true, 4),


INSERT INTO clazz(class_id, number_of_student, start_date, end_date, total_course_hours, course_book)
VALUES
('SKA1-Aa1401', 20, '2022-03-15', '2022-06-15', 42, 'LOOK1'),
('SKA2-Aa1402', 18, '2022-03-15', '2022-06-15', 42, 'LOOK1'),
('SKB1-Ab1408', 19, )

INSERT INTO assignment_detail(start_date, end_date, expected_hours, active_hours, leave_note, pay_rate, contract_id, class_id)
VALUES

INSERT INTO payment(transferred_date, transferred_amount, payment_type, assignment_detail_id)
VALUES