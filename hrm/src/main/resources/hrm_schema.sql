CREATE TABLE IF NOT EXISTS job_offer (
    job_id VARCHAR PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    level VARCHAR(20) NOT NULL,
    requirement VARCHAR(200) NOT NULL,
    description VARCHAR(255) NOT NULL,
    status VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS application_form (
    form_id VARCHAR PRIMARY KEY,
    first_name VARCHAR(30) NOT NULL,
    last_name VARCHAR(30) NOT NULL,
    email VARCHAR(40) NOT NULL,
    mobile_phone VARCHAR(10) NOT NULL,
    about_yourself VARCHAR(255),
    employment_status VARCHAR(30) NOT NULL,
    job_id VARCHAR REFERENCES job_offer(job_id)
);