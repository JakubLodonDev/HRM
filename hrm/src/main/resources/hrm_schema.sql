DROP TABLE employment_source;

DROP TABLE application_form_audit;

DROP TABLE application_form;

DROP TABLE form_attachment;

DROP TABLE hr_user;

DROP TABLE hr_identification;

DROP TABLE hr_role;

DROP TABLE employee;

DROP TABLE cv_attachment;

DROP TABLE employee_address;

DROP TABLE job_offer;

CREATE TABLE IF NOT EXISTS hr_role (
    role_id UUID PRIMARY KEY,
    role_name VARCHAR(30) NOT NULL
);

CREATE TABLE IF NOT EXISTS hr_identification (
    identification_id UUID PRIMARY KEY,
    login VARCHAR(50) NOT NULL,
    pwd VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS hr_user (
    user_id UUID PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(50) NOT NULL,
    is_password_change_required BOOLEAN NOT NULL,
    identification_id UUID REFERENCES hr_identification(identification_id),
    role_id UUID REFERENCES hr_role(role_id)
);

CREATE TABLE IF NOT EXISTS job_offer (
    job_id UUID PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    level VARCHAR(20) NOT NULL,
    requirement VARCHAR(200) NOT NULL,
    description VARCHAR(255) NOT NULL,
    status VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS form_attachment (
    form_attachment_id UUID PRIMARY KEY,
    file_name VARCHAR(255),
    file_type VARCHAR(50),
    data BYTEA
);

CREATE TABLE IF NOT EXISTS application_form (
    form_id UUID PRIMARY KEY,
    first_name VARCHAR(30) NOT NULL,
    last_name VARCHAR(30) NOT NULL,
    email VARCHAR(40) NOT NULL,
    mobile_phone VARCHAR(10) NOT NULL,
    street_address VARCHAR(30) NOT NULL,
    city VARCHAR(20) NOT NULL,
    country VARCHAR(20) NOT NULL,
    zip_code VARCHAR(5) NOT NULL,
    about_yourself VARCHAR(255),
    employment_status VARCHAR(30) NOT NULL,
    job_id UUID REFERENCES job_offer(job_id),
    form_attachment_id UUID UNIQUE REFERENCES form_attachment(form_attachment_id)
);

CREATE TABLE IF NOT EXISTS application_form_audit (
    application_form_audit_id UUID PRIMARY KEY,
    date_modified TIMESTAMP NOT NULL,
    form_id UUID REFERENCES application_form(form_id),
    user_id UUID REFERENCES hr_user(user_id)
);

CREATE TABLE IF NOT EXISTS employee_address (
    employee_address_id UUID PRIMARY KEY,
    street_address VARCHAR(30) NOT NULL,
    city VARCHAR(20) NOT NULL,
    country VARCHAR(20) NOT NULL,
    zip_code VARCHAR(5) NOT NULL
);

CREATE TABLE IF NOT EXISTS cv_attachment (
    cv_attachment_id UUID PRIMARY KEY,
    file_name VARCHAR(255),
    file_type VARCHAR(50),
    data BYTEA
);

CREATE TABLE IF NOT EXISTS employee (
    employee_id UUID PRIMARY KEY,
    first_name VARCHAR(30) NOT NULL,
    last_name VARCHAR(30) NOT NULL,
    email VARCHAR(40) NOT NULL,
    mobile_phone VARCHAR(10) NOT NULL,
    employment_status VARCHAR(30) NOT NULL,
    position_name VARCHAR(30) NOT NULL,
    employee_address_id UUID REFERENCES employee_address(employee_address_id),
    cv_attachment_id UUID UNIQUE REFERENCES cv_attachment(cv_attachment_id)
);

CREATE TABLE IF NOT EXISTS employment_source (
    employment_source_id UUID PRIMARY KEY,
    source_type VARCHAR(100) NOT NULL,
    form_id UUID REFERENCES application_form(form_id),
    employee_id UUID REFERENCES employee(employee_id)
);
