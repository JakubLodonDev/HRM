DROP TABLE application_form;

DROP TABLE job_offer;

DROP TABLE address;

DROP TABLE hr_user;

DROP TABLE hr_role;

CREATE TABLE IF NOT EXISTS job_offer (
    job_id UUID PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    level VARCHAR(20) NOT NULL,
    requirement VARCHAR(200) NOT NULL,
    description VARCHAR(255) NOT NULL,
    status VARCHAR(20) NOT NULL
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
    job_id UUID REFERENCES job_offer(job_id)
);

CREATE TABLE IF NOT EXISTS hr_role (
    role_id UUID PRIMARY KEY,
    role_name VARCHAR(30) NOT NULL
);

CREATE TABLE IF NOT EXISTS hr_user (
    user_id UUID PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(50) NOT NULL,
    pwd VARCHAR(200) NOT NULL,
    role_id UUID REFERENCES hr_role(role_id)
);