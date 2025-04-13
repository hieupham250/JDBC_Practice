create database practive_db;
use practive_db;
create table accounts
(
    id int auto_increment primary key,
    username varchar(255),
    password varchar(255),
    role enum ('admin','hr') not null,
    create_at timestamp default current_timestamp(),
    status boolean
);

create table departments
(
    dp_id int auto_increment primary key,
    dp_name varchar(255) not null unique check ( char_length(dp_name) > 10 && char_length(dp_name) < 100 ),
    dp_description varchar(255),
    dp_status boolean
);
create table employee
(
    employee_id varchar(5) primary key,
    full_name varchar(150) not null check (char_length(full_name) between 15 and 150),
    email varchar(255) not null unique,
    phone_number varchar(15) not null,
    gender enum ('male', 'female', 'other') not null,
    salary_level int not null check (salary_level > 0),
    salary decimal(15, 2) not null check (salary > 0),
    birth_date date not null,
    address text not null,
    status enum ('active', 'inactive', 'onleave', 'policyleave') not null,
    department_id int not null,
    foreign key (department_id) references departments (dp_id),
    check (employee_id regexp '^E[0-9]{4}$'),
    check (email regexp '^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$'),
    check (phone_number regexp '^0[0-9]{9}$')
);

insert into departments (dp_name, dp_description, dp_status)
values
    ('Phòng Nhân sự Tổng hợp', 'Quản lý nhân sự và tuyển dụng', true),
    ('Phòng Kế toán Tài chính', 'Theo dõi tài chính và kế toán', true),
    ('Phòng Kỹ thuật Hệ thống', 'Phát triển phần mềm và hạ tầng', true),
    ('Phòng Marketing Chiến lược', 'Chiến lược quảng bá và truyền thông', true),
    ('Phòng Bán hàng & CSKH', 'Quản lý bán hàng và chăm sóc khách hàng', true),
    ('Phòng Công nghệ Thông tin', 'Hỗ trợ kỹ thuật và quản trị hệ thống', true),
    ('Phòng Pháp chế Doanh nghiệp', 'Tư vấn và xử lý pháp lý', true),
    ('Phòng Nghiên cứu & Phát triển', 'Nghiên cứu và phát triển sản phẩm mới', true),
    ('Phòng Thiết kế Sáng tạo', 'Thiết kế giao diện và nhận diện thương hiệu', true),
    ('Phòng Điều hành Sản xuất', 'Điều phối hoạt động doanh nghiệp', true),
    ('Phòng Hành chính Văn phòng', 'Quản lý văn phòng và tài sản', true),
    ('Phòng Đào tạo Nhân sự', 'Đào tạo và phát triển nhân sự', true),
    ('Phòng Chăm sóc Khách hàng', 'Hỗ trợ khách hàng sau bán', true),
    ('Phòng Quản lý Sản xuất', 'Quản lý sản xuất và vận hành', true),
    ('Phòng Logistics Kho vận', 'Quản lý kho vận và vận chuyển', true),
    ('Phòng Kiểm thử Chất lượng', 'Kiểm tra chất lượng phần mềm', true),
    ('Phòng Quản lý Dự án', 'Quản lý và giám sát tiến độ dự án', false),
    ('Phòng An ninh Dữ liệu', 'Đảm bảo an toàn thông tin và dữ liệu', false),
    ('Phòng Trách nhiệm Xã hội', 'Trách nhiệm xã hội của doanh nghiệp', false),
    ('Phòng Tài nguyên Môi trường', 'Quản lý tài nguyên và môi trường', true);

insert into employee (employee_id, full_name, email, phone_number, gender, salary_level, salary, birth_date, address, status, department_id)
values
    ('E0001', 'Nguyễn Văn An       ', 'an.nguyen01@example.com', '0123456789', 'male', 2, 12000000.00, '1990-05-12', 'Hà Nội', 'active', 11),
    ('E0002', 'Trần Thị Bích Hạnh  ', 'hanh.tran02@example.com', '0987654321', 'female', 3, 15000000.00, '1988-07-25', 'TP HCM', 'active', 2),
    ('E0003', 'Lê Minh Tuấn        ', 'tuan.le03@example.com', '0912345678', 'male', 1, 10000000.00, '1995-11-30', 'Đà Nẵng', 'active', 3),
    ('E0004', 'Phạm Thị Hương      ', 'huong.pham04@example.com', '0909123456', 'female', 2, 12000000.00, '1992-03-18', 'Hải Phòng', 'onleave', 4),
    ('E0005', 'Đỗ Văn Cường        ', 'cuong.do05@example.com', '0934567890', 'male', 4, 18000000.00, '1987-09-05', 'Cần Thơ', 'active', 5),
    ('E0006', 'Ngô Thị Mai         ', 'mai.ngo06@example.com', '0967890123', 'female', 1, 9000000.00, '1993-12-01', 'Huế', 'active', 6),
    ('E0007', 'Vũ Minh Khôi        ', 'khoi.vu07@example.com', '0978901234', 'male', 2, 11500000.00, '1990-06-20', 'Quảng Ninh', 'inactive', 7),
    ('E0008', 'Bùi Thị Lan         ', 'lan.bui08@example.com', '0943210987', 'female', 3, 14000000.00, '1989-01-15', 'Nam Định', 'active', 8),
    ('E0009', 'Hoàng Văn Đức       ', 'duc.hoang09@example.com', '0923456789', 'male', 2, 12500000.00, '1991-08-10', 'Nghệ An', 'policyleave', 9),
    ('E0010', 'Lý Thị Kim Ngân     ', 'ngan.ly10@example.com', '0956789012', 'female', 2, 13000000.00, '1994-04-22', 'Bình Dương', 'active', 10);

delimiter \\
create procedure login(
    username_in varchar(255),
    password_in varchar(255)
)
begin
    select *from accounts where username = username_in and password = password_in;
end;

create procedure get_all_department()
begin
    select * from departments;
end;

create procedure get_department_by_page(size int, offset int)
begin
    select * from departments limit size offset offset;
end;

create procedure create_department(
    name varchar(255),description varchar(255), status boolean
)
begin
    insert into departments (dp_name, dp_description, dp_status)
    values (name,description,status);
end;

create procedure update_department(id_in int, name_in varchar(255),des_in varchar(255),status_in boolean)
begin
    update departments
    set
        dp_name = name_in,
        dp_description = des_in,
        dp_status = status_in
    where dp_id = id_in;
end;

create procedure delete_department(id int)
begin
    delete from departments where dp_id = id;
end;

create procedure get_department_by_name(name varchar(255))
begin
    select *from departments where dp_name = name;
end;

create procedure get_all_employees()
begin
    select *from employee;
end;

create procedure get_employee_by_page(size int, offset int)
begin
    select * from employee limit size offset offset;
end;

create procedure create_employee(employee_id varchar(5),
                                 full_name varchar(150),
                                 email varchar(255),
                                 phone_number varchar(15),
                                 gender enum('male', 'female', 'other'),
                                 salary_level int,
                                 salary decimal(15,2),
                                 birth_date date,
                                 address text,
                                 status enum('active', 'inactive', 'onleave', 'policyleave'),
                                 department_id int)
begin
    insert into employee (
        employee_id, full_name, email, phone_number, gender,
        salary_level, salary, birth_date, address, status, department_id
    )
    values (
               employee_id, full_name, email, phone_number, gender,
               salary_level, salary, birth_date, address, status, department_id
           );
end;

create procedure delete_employee(emp_id int)
begin
    delete from employee where employee_id = emp_id;
end;

create procedure get_employee_by_name(emp_name varchar(100))
begin
    select * from employee where full_name like concat('%', emp_name, '%');
end;

create procedure search_employee_by_age_range(min_age int, max_age int)
begin
    select * from employee where timestampdiff(year, birth_date, curdate()) between min_age and max_age;
end;

create procedure sort_employees_by_salary_desc()
begin
    select * from employee order by salary desc;
end;

create procedure sort_employees_by_name_asc()
begin
    select * from employee order by asc;
end;
delimiter \\;
