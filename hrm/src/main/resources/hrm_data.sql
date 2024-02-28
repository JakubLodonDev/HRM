INSERT INTO job_offer (job_id, name, level, requirement, description, status)
VALUES ('550e8400-e29b-41d4-a716-446655440000', 'Junior Project Manager', 'Junior', 'Be good', 'We want you', 'Open');

INSERT INTO job_offer (job_id, name, level, requirement, description, status)
VALUES ('550e8400-e29b-41d4-a716-446655440001', 'Java Developer', 'Mid', 'Be not good', 'We want you', 'Open');

INSERT INTO job_offer (job_id, name, level, requirement, description, status)
VALUES ('550e8400-e29b-41d4-a716-446655440002', 'Project Manager', 'Senior', 'Be the best', 'We want you', 'Open');

INSERT INTO `roles` (role_id, role_name)
VALUES ('550e8400-e29b-41d4-a716-446655440030', 'ADMIN');

INSERT INTO `roles` (role_id, role_name)
VALUES ('550e8400-e29b-41d4-a716-446655440031', 'MANAGER');

INSERT INTO `roles` (role_id, role_name)
VALUES ('550e8400-e29b-41d4-a716-446655440032', 'RECRUITMENT_SPECIALIST');

INSERT INTO `user` (user_id, first_name, last_name, email, pwd, role_id)
VALUES ('550e8400-e29b-41d4-a716-446655440042', 'John', 'Smith', 'john.smith@jo.pl', '12345', '550e8400-e29b-41d4-a716-446655440030');

INSERT INTO `user` (user_id, first_name, last_name, email, pwd, role_id)
VALUES ('550e8400-e29b-41d4-a716-446655440042', 'Anna', 'Johan', 'anna.johan@jo.pl', '12345', '550e8400-e29b-41d4-a716-446655440031');

INSERT INTO `user` (user_id, first_name, last_name, email, pwd, role_id)
VALUES ('550e8400-e29b-41d4-a716-446655440042', 'Joe', 'Kim', 'joe.kim@jo.pl', '12345', '550e8400-e29b-41d4-a716-446655440032');