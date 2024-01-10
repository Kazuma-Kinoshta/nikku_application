CREATE TABLE IF NOT EXISTS school(
    school_id CHAR(5) PRIMARY KEY,
    school_name VARCHAR NOT NULL,
    school_type CHAR(1) NOT NULL,
    school_prefecture VARCHAR NOT NULL,
    mail VARCHAR NOT NULL,
    tell VARCHAR,
    available BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS users(
    user_id CHAR(10) PRIMARY KEY,
    password CHAR(10) NOT NULL,
    last_name VARCHAR NOT NULL,
    first_name VARCHAR NOT NULL,
    mail VARCHAR,
    school_id CHAR(5) NOT NULL,
    authority CHAR(1) NOT NULL,
    user_class VARCHAR,
    grade CHAR(1),
    student_number CHAR(3)
);

CREATE TABLE IF NOT EXISTS diary(
    user_id CHAR(10),
    diary_date DATE,
    diary_text VARCHAR,
    checked BOOLEAN,
    school_id CHAR(5),
    PRIMARY KEY(user_id,diary_date)
);

CREATE TABLE IF NOT EXISTS questionnare(
    user_id CHAR(10),
    diary_date DATE,
    feelings CHAR(1),
    breakfast BOOLEAN,
    sleep_time TIME,
    wake_time TIME,
    not_sleep BOOLEAN,
    PRIMARY KEY(user_id,diary_date)
);

CREATE TABLE IF NOT EXISTS consultation(
    consultation_id CHAR(16) PRIMARY KEY,
    user_id CHAR(10) NOT NULL,
    consultation_date DATETIME NOT NULL,
    consultation_text VARCHAR,
    school_id CHAR(5) NOT NULL
);

CREATE TABLE IF NOT EXISTS response(
    consultation_id CHAR(16) NOT NULL,
    response_number CHAR(10) NOT NULL,
    response_user_id CHAR(10) NOT NULL,
    response_text VARCHAR,
    PRIMARY KEY(consultation_id,response_number)
);

CREATE TABLE IF NOT EXISTS consultation_check(
    consultation_id CHAR(16) NOT NULL,
    check_user_id CHAR(10) NOT NULL,
    PRIMARY KEY(consultation_id,check_user_id)
)
