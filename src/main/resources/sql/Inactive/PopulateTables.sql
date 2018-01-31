INSERT INTO users(user_name,first_name,last_name,email,password,salt) VALUES
('TEMP1','Aparna','Mahadev','Mahadev','',''),
('A1K','Kabir','Ahmedul','Ahmedul','',''),
('BN1','Blake','Nelson','Nelson','',''),
('CEW','Craig','Willis','Willis','',''),
('CKN','Chun-Kit','Ngan','Ngan','',''),
('CR','Carolina','Ruiz','Ruiz','',''),
('CR1','Charles','Rich','Rich','',''),
('CS2','Craig','Shue','Shue','',''),
('CP4','Carol','Pinciroli','Pinciroli','',''),
('DJD','Dan','Dougherty','Dougherty','',''),
('DK2','Dimitry','Korkin','Korkin','',''),
('DS4','Doug','Selent','Selent','',''),
('EAR','Elke','Rundensteiner','Rundensteiner','',''),
('EOA','Emmanuel','Agu','Agu','',''),
('GFP','Gary','Pollice','Pollice','',''),
('GMH','Glynis','Hamel','Hamel','',''),
('GTH','Gorge','Heineman','Heineman','',''),
('GXS','Gabor ','Sarkozy','Sarkozy','',''),
('TEMP2','Hao','Loi','Loi','',''),
('HCL','Huge','Laurer','Laurer','',''),
('HJS','Herman','Servatius','Servatius','',''),
('JB7','Joe','Beck','Beck','',''),
('JJB','Jerry','Breecher','Breecher','',''),
('J1C','Joushua','Cuneo','Cuneo','',''),
('JSZ','J','Singh','Singh','',''),
('JT1','Jorge','Toro','Toro','',''),
('JRW','Jacob','Whitehill','Whitehill','',''),
('KKV','Krishna','Venkatasubramanian','Venkatasubramanian','',''),
('KP','Keith','Pray','Pray','',''),
('LTH','Lane','Harrison','Harrison','',''),
('MKH','Micha','Hofri','Hofri','',''),
('MLC','Mark','Claypool','Claypool','',''),
('MXC','Mike','Ciaraldi','Ciaraldi','',''),
('MXG','Mike','Gennert','Gennert','',''),
('MYE','Mohammed Y.','Eltabark','Eltabark','',''),
('NTH','Neil','Heffernan','Heffernan','',''),
('RJW','Robert','Walls','Walls','',''),
('RN1','Rodica','Neamtu','Neamtu','',''),
('SIM','Suzanne','Mello-Stark','Mello-Stark','',''),
('SL4','Susan','Landau','Landau','',''),
('TG1','Tian','Guo','Guo','',''),
('W1W','Wilson','Wong','Wong','',''),
('XK1','Xiangan','Kong','Kong','',''),
('YL2','Yanhua','Li','Li','','');

INSERT INTO user_permissions(users_id, is_admin, is_coursebuilder, is_faculty) VALUES
((SELECT id FROM users WHERE user_name = 'TEMP1'), FALSE, FALSE, TRUE),
((SELECT id FROM users WHERE user_name = 'A1K'), FALSE, FALSE, TRUE),
((SELECT id FROM users WHERE user_name = 'BN1'), FALSE, FALSE, TRUE),
((SELECT id FROM users WHERE user_name = 'CEW'), FALSE, FALSE, TRUE),
((SELECT id FROM users WHERE user_name = 'CKN'), FALSE, FALSE, TRUE),
((SELECT id FROM users WHERE user_name = 'CR'), FALSE, FALSE, TRUE),
((SELECT id FROM users WHERE user_name = 'CR1'), FALSE, FALSE, TRUE),
((SELECT id FROM users WHERE user_name = 'CS2'), FALSE, FALSE, TRUE),
((SELECT id FROM users WHERE user_name = 'CP4'), FALSE, FALSE, TRUE),
((SELECT id FROM users WHERE user_name = 'DJD'), FALSE, FALSE, TRUE),
((SELECT id FROM users WHERE user_name = 'DK2'), FALSE, FALSE, TRUE),
((SELECT id FROM users WHERE user_name = 'DS4'), FALSE, FALSE, TRUE),
((SELECT id FROM users WHERE user_name = 'EAR'), FALSE, FALSE, TRUE),
((SELECT id FROM users WHERE user_name = 'EOA'), FALSE, FALSE, TRUE),
((SELECT id FROM users WHERE user_name = 'GFP'), FALSE, FALSE, TRUE),
((SELECT id FROM users WHERE user_name = 'GMH'), FALSE, FALSE, TRUE),
((SELECT id FROM users WHERE user_name = 'GTH'), FALSE, FALSE, TRUE),
((SELECT id FROM users WHERE user_name = 'GXS'), FALSE, FALSE, TRUE),
((SELECT id FROM users WHERE user_name = 'TEMP2'), FALSE, FALSE, TRUE),
((SELECT id FROM users WHERE user_name = 'HCL'), FALSE, FALSE, TRUE),
((SELECT id FROM users WHERE user_name = 'HJS'), FALSE, FALSE, TRUE),
((SELECT id FROM users WHERE user_name = 'JB7'), FALSE, FALSE, TRUE),
((SELECT id FROM users WHERE user_name = 'JJB'), FALSE, FALSE, TRUE),
((SELECT id FROM users WHERE user_name = 'J1C'), FALSE, FALSE, TRUE),
((SELECT id FROM users WHERE user_name = 'JSZ'), FALSE, FALSE, TRUE),
((SELECT id FROM users WHERE user_name = 'JT1'), FALSE, FALSE, TRUE),
((SELECT id FROM users WHERE user_name = 'JRW'), FALSE, FALSE, TRUE),
((SELECT id FROM users WHERE user_name = 'KKV'), FALSE, FALSE, TRUE),
((SELECT id FROM users WHERE user_name = 'KP'), FALSE, FALSE, TRUE),
((SELECT id FROM users WHERE user_name = 'LTH'), FALSE, FALSE, TRUE),
((SELECT id FROM users WHERE user_name = 'MKH'), FALSE, FALSE, TRUE),
((SELECT id FROM users WHERE user_name = 'MLC'), FALSE, FALSE, TRUE),
((SELECT id FROM users WHERE user_name = 'MXC'), FALSE, FALSE, TRUE),
((SELECT id FROM users WHERE user_name = 'MXG'), FALSE, FALSE, TRUE),
((SELECT id FROM users WHERE user_name = 'MYE'), FALSE, FALSE, TRUE),
((SELECT id FROM users WHERE user_name = 'NTH'), FALSE, FALSE, TRUE),
((SELECT id FROM users WHERE user_name = 'RJW'), FALSE, FALSE, TRUE),
((SELECT id FROM users WHERE user_name = 'RN1'), FALSE, FALSE, TRUE),
((SELECT id FROM users WHERE user_name = 'SIM'), FALSE, FALSE, TRUE),
((SELECT id FROM users WHERE user_name = 'SL4'), FALSE, FALSE, TRUE),
((SELECT id FROM users WHERE user_name = 'TG1'), FALSE, FALSE, TRUE),
((SELECT id FROM users WHERE user_name = 'W1W'), FALSE, FALSE, TRUE),
((SELECT id FROM users WHERE user_name = 'XK1'), FALSE, FALSE, TRUE),
((SELECT id FROM users WHERE user_name = 'YL2'), FALSE, FALSE, TRUE)


INSERT INTO terms(term_name) VALUES
('A'),
('B'),
('C'),
('D'),
('E1'),
('E2');

INSERT INTO locations(building,room,room_size) VALUES
('HL',230,45);

INSERT INTO courses(course_name,course_description) VALUES
('201',''),
('453',''),
('502',''),
('503',''),
('509',''),
('513',''),
('525',''),
('528',''),
('534',''),
('539',''),
('542',''),
('543',''),
('548',''),
('558',''),
('565',''),
('583',''),
('584',''),
('585',''),
('586',''),
('1004',''),
('1101',''),
('1102',''),
('2011',''),
('2012',''),
('2022',''),
('2043',''),
('2102',''),
('2119',''),
('2223',''),
('2223',''),
('2301',''),
('2302',''),
('3013',''),
('3041',''),
('3043',''),
('3133',''),
('3431',''),
('3516',''),
('3733',''),
('4120',''),
('4123',''),
('4233',''),
('4341',''),
('4401',''),
('4432',''),
('4445',''),
('4513',''),
('4518',''),
('4536',''),
('4731',''),
('4803',''),
('5003',''),
('5007',''),
('23011',''),
('GPS',''),
('ProjA17',''),
('ProjC18','');

INSERT INTO departments(department) VALUES
('CS');

INSERT INTO faculty(users_id,required_credits) VALUES
((SELECT id FROM users WHERE user_name = 'TEMP1'),1);

INSERT INTO faculty_departments(faculty_id,departments_id) VALUES
((SELECT id FROM faculty WHERE users_id = (SELECT id FROM users WHERE user_name = 'TEMP1')),(SELECT id FROM departments WHERE department = 'CS'));

INSERT INTO course_departments(courses_id,departments_id,course_number) VALUES
((SELECT id FROM courses WHERE course_name = '1004'),(SELECT id FROM departments WHERE department = 'CS'),1004);

INSERT INTO course_sections(courses_id,section_type) VALUES
((SELECT id FROM courses WHERE course_name = '1004'),'C01');

INSERT INTO course_times(course_sections_id,day_of_week,start_time,end_time,locations_id) VALUES
((SELECT id FROM course_sections WHERE courses_id = (SELECT id FROM courses WHERE course_name = '1004')),1,1,2,(SELECT id FROM locations WHERE building = 'HL' AND room = 220));

INSERT INTO faculty_courses(faculty_id,course_sections_id) VALUES
((SELECT id FROM faculty WHERE users_id = (SELECT id FROM users WHERE user_name = 'TEMP1')),(SELECT id FROM course_sections WHERE courses_id = (SELECT id FROM courses WHERE course_name = '1004')));

INSERT INTO course_requests(faculty_id,course_sections_id) VALUES
((SELECT id FROM faculty WHERE users_id = (SELECT id FROM users WHERE user_name = 'TEMP1')),(SELECT id FROM course_sections WHERE courses_id = (SELECT id FROM courses WHERE course_name = '1004')));

INSERT INTO course_sections_terms(course_sections_id,terms_id) VALUES
((SELECT id FROM course_sections WHERE courses_id = (SELECT id FROM courses WHERE course_name = '1004')),(SELECT id FROM terms WHERE term_name = 'C'));
