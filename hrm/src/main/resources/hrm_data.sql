INSERT INTO job_offer (job_id, name, level, requirement, description, status)
VALUES ('550e8400-e29b-41d4-a716-446655440000', 'Junior Project Manager', 'Junior', 'Be good', 'We want you', 'Open');

INSERT INTO job_offer (job_id, name, level, requirement, description, status)
VALUES ('550e8400-e29b-41d4-a716-446655440001', 'Java Developer', 'Mid', 'Be not good', 'We want you', 'Open');

INSERT INTO job_offer (job_id, name, level, requirement, description, status)
VALUES ('550e8400-e29b-41d4-a716-446655440002', 'Project Manager', 'Senior', 'Be the best', 'We want you', 'Open');

INSERT INTO hr_role (role_id, role_name)
VALUES ('550e8400-e29b-41d4-a716-446655440030', 'ADMIN');

INSERT INTO hr_role (role_id, role_name)
VALUES ('550e8400-e29b-41d4-a716-446655440031', 'MANAGER');

INSERT INTO hr_role (role_id, role_name)
VALUES ('550e8400-e29b-41d4-a716-446655440032', 'RECRUITMENT_SPECIALIST');

INSERT INTO hr_identification (identification_id, login, pwd)
VALUES ('550e8400-e29b-41d4-a716-446655440040', 'john.smith@jo.pl', '$2a$12$PtCy.WWhlj.S73bp2p3b4eBJxoJ.YdqHzUDSRfZ6Um79g44VsKjH6');

INSERT INTO hr_identification (identification_id, login, pwd)
VALUES ('550e8400-e29b-41d4-a716-446655440041', 'anna.johan@jo.pl', '$2a$12$PtCy.WWhlj.S73bp2p3b4eBJxoJ.YdqHzUDSRfZ6Um79g44VsKjH6');

INSERT INTO hr_identification (identification_id, login, pwd)
VALUES ('550e8400-e29b-41d4-a716-446655440042', 'joe.kim@jo.pl', '$2a$12$PtCy.WWhlj.S73bp2p3b4eBJxoJ.YdqHzUDSRfZ6Um79g44VsKjH6');

INSERT INTO hr_user (user_id, first_name, last_name, email, is_password_change_required ,identification_id, role_id)
VALUES ('550e8400-e29b-41d4-a716-446655440050', 'John', 'Smith', 'john.smith@jo.pl', TRUE, '550e8400-e29b-41d4-a716-446655440040', '550e8400-e29b-41d4-a716-446655440030');

INSERT INTO hr_user (user_id, first_name, last_name, email, is_password_change_required, identification_id, role_id)
VALUES ('550e8400-e29b-41d4-a716-446655440051', 'Anna', 'Johan', 'anna.johan@jo.pl', TRUE, '550e8400-e29b-41d4-a716-446655440041', '550e8400-e29b-41d4-a716-446655440031');

INSERT INTO hr_user (user_id, first_name, last_name, email, is_password_change_required, identification_id, role_id)
VALUES ('550e8400-e29b-41d4-a716-446655440052', 'Joe', 'Kim', 'joe.kim@jo.pl', TRUE,'550e8400-e29b-41d4-a716-446655440042', '550e8400-e29b-41d4-a716-446655440032');











INSERT INTO application_form (form_id, first_name, last_name, email, mobile_phone, street_address, city, country, zip_code, about_yourself, employment_status, job_id)
VALUES ('550e8400-e29b-41d4-a716-446655440060', 'Jako', 'Lolo', 'jakololo@gmail.com', '9283742657', 'Kurza 5', 'Warszawa', 'Polska', '02020', '', 'Process', '550e8400-e29b-41d4-a716-446655440000');

INSERT INTO application_form (form_id, first_name, last_name, email, mobile_phone, street_address, city, country, zip_code, about_yourself, employment_status, job_id)
VALUES ('550e8400-e29b-41d4-a716-446655440061', 'Aro', 'Koko', 'arrokoko@gmail.com', '9283742657', 'Kacza 5', 'Warszawa', 'Polska', '92020', '', 'Process', '550e8400-e29b-41d4-a716-446655440000');

INSERT INTO application_form (form_id, first_name, last_name, email, mobile_phone, street_address, city, country, zip_code, about_yourself, employment_status, job_id)
VALUES ('550e8400-e29b-41d4-a716-446655440062', 'Tako', 'Mako', 'takomako@gmail.com', '9284742657', 'Pawia 2', 'Warszawa', 'Polska', '92450', '', 'Process', '550e8400-e29b-41d4-a716-446655440000');

INSERT INTO application_form (form_id, first_name, last_name, email, mobile_phone, street_address, city, country, zip_code, about_yourself, employment_status, job_id)
VALUES ('550e8400-e29b-41d4-a716-446655440063', 'Jako', 'Lolo', 'jakololo@gmail.com', '9283742657', 'Kurza 5', 'Warszawa', 'Polska', '02020', '', 'Process', '550e8400-e29b-41d4-a716-446655440000');

INSERT INTO application_form (form_id, first_name, last_name, email, mobile_phone, street_address, city, country, zip_code, about_yourself, employment_status, job_id)
VALUES ('550e8400-e29b-41d4-a716-446655440064', 'Aro', 'Koko', 'arrokoko@gmail.com', '9283742657', 'Kacza 5', 'Warszawa', 'Polska', '92020', '', 'Process', '550e8400-e29b-41d4-a716-446655440000');

INSERT INTO application_form (form_id, first_name, last_name, email, mobile_phone, street_address, city, country, zip_code, about_yourself, employment_status, job_id)
VALUES ('550e8400-e29b-41d4-a716-446655440065', 'Tako', 'Mako', 'takomako@gmail.com', '9284742657', 'Pawia 2', 'Warszawa', 'Polska', '92450', '', 'Process', '550e8400-e29b-41d4-a716-446655440000');

INSERT INTO application_form (form_id, first_name, last_name, email, mobile_phone, street_address, city, country, zip_code, about_yourself, employment_status, job_id)
VALUES ('550e8400-e29b-41d4-a716-446655440066', 'Jako', 'Lolo', 'jakololo@gmail.com', '9283742657', 'Kurza 5', 'Warszawa', 'Polska', '02020', '', 'Process', '550e8400-e29b-41d4-a716-446655440000');

INSERT INTO application_form (form_id, first_name, last_name, email, mobile_phone, street_address, city, country, zip_code, about_yourself, employment_status, job_id)
VALUES ('550e8400-e29b-41d4-a716-446655440067', 'Aro', 'Koko', 'arrokoko@gmail.com', '9283742657', 'Kacza 5', 'Warszawa', 'Polska', '92020', '', 'Process', '550e8400-e29b-41d4-a716-446655440000');

INSERT INTO application_form (form_id, first_name, last_name, email, mobile_phone, street_address, city, country, zip_code, about_yourself, employment_status, job_id)
VALUES ('550e8400-e29b-41d4-a716-446655440068', 'Tako', 'Mako', 'takomako@gmail.com', '9284742657', 'Pawia 2', 'Warszawa', 'Polska', '92450', '', 'Process', '550e8400-e29b-41d4-a716-446655440000');
