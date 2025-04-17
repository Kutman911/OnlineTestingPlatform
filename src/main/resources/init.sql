-- init.sql: Database initialization for Online Testing Platform

CREATE TABLE users (
                       user_id SERIAL PRIMARY KEY,
                       username VARCHAR(50) UNIQUE NOT NULL,
                       password VARCHAR(100) NOT NULL,
                       email VARCHAR(100) UNIQUE NOT NULL,
                       role VARCHAR(20) NOT NULL CHECK (role IN ('ADMIN', 'TEACHER', 'STUDENT', 'COURSE_MANAGER')),
                       first_name VARCHAR(50),
                       last_name VARCHAR(50),
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE courses (
                         course_id SERIAL PRIMARY KEY,
                         title VARCHAR(100) NOT NULL,
                         description TEXT,
                         created_by INTEGER REFERENCES users(user_id),
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE tests (
                       test_id SERIAL PRIMARY KEY,
                       course_id INTEGER REFERENCES courses(course_id),
                       title VARCHAR(100) NOT NULL,
                       description TEXT,
                       time_limit_minutes INTEGER,
                       max_score INTEGER,
                       is_published BOOLEAN DEFAULT FALSE,
                       created_by INTEGER REFERENCES users(user_id),
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE questions (
                           question_id SERIAL PRIMARY KEY,
                           test_id INTEGER REFERENCES tests(test_id),
                           question_text TEXT NOT NULL,
                           question_type VARCHAR(20) CHECK (question_type IN ('SINGLE_CHOICE', 'MULTIPLE_CHOICE', 'TEXT_ANSWER')),
                           points INTEGER DEFAULT 1,
                           options JSONB,
                           correct_answer JSONB
);

CREATE TABLE test_attempts (
                               attempt_id SERIAL PRIMARY KEY,
                               test_id INTEGER REFERENCES tests(test_id),
                               user_id INTEGER REFERENCES users(user_id),
                               start_time TIMESTAMP,
                               end_time TIMESTAMP,
                               score INTEGER,
                               status VARCHAR(20) DEFAULT 'IN_PROGRESS' CHECK (status IN ('IN_PROGRESS', 'COMPLETED', 'GRADED'))
);

CREATE TABLE answers (
                         answer_id SERIAL PRIMARY KEY,
                         attempt_id INTEGER REFERENCES test_attempts(attempt_id),
                         question_id INTEGER REFERENCES questions(question_id),
                         answer_data JSONB,
                         is_correct BOOLEAN,
                         points_awarded INTEGER
);

CREATE TABLE course_enrollments (
                                    enrollment_id SERIAL PRIMARY KEY,
                                    course_id INTEGER REFERENCES courses(course_id),
                                    user_id INTEGER REFERENCES users(user_id),
                                    enrolled_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_users_username ON users(username);
CREATE INDEX idx_tests_course ON tests(course_id);
CREATE INDEX idx_questions_test ON questions(test_id);

INSERT INTO users (username, password, email, role, first_name, last_name)
VALUES ('admin', 'admin123', 'admin@university.edu', 'ADMIN', 'System', 'Administrator');