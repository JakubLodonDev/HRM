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

INSERT INTO hr_identification (identification_id, login)
VALUES ('550e8400-e29b-41d4-a716-446655440040', 'john.smith@jo.pl');

INSERT INTO hr_identification (identification_id, login)
VALUES ('550e8400-e29b-41d4-a716-446655440041', 'anna.johan@jo.pl');

INSERT INTO hr_identification (identification_id, login)
VALUES ('550e8400-e29b-41d4-a716-446655440042', 'joe.kim@jo.pl');

INSERT INTO hr_user (user_id, first_name, last_name, email, identification_id, role_id)
VALUES ('550e8400-e29b-41d4-a716-446655440050', 'John', 'Smith', 'john.smith@jo.pl', '550e8400-e29b-41d4-a716-446655440040', '550e8400-e29b-41d4-a716-446655440030');

INSERT INTO hr_user (user_id, first_name, last_name, email, identification_id, role_id)
VALUES ('550e8400-e29b-41d4-a716-446655440051', 'Anna', 'Johan', 'anna.johan@jo.pl', '550e8400-e29b-41d4-a716-446655440041', '550e8400-e29b-41d4-a716-446655440031');

INSERT INTO hr_user (user_id, first_name, last_name, email, identification_id, role_id)
VALUES ('550e8400-e29b-41d4-a716-446655440052', 'Joe', 'Kim', 'joe.kim@jo.pl', '550e8400-e29b-41d4-a716-446655440042', '550e8400-e29b-41d4-a716-446655440032');


