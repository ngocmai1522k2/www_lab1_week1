# www_lab_week1
## Sinh viên thực hiện:

Họ tên: [Lê Thị Ngọc Mai]

MSSV: [20005501]

## Đề bài thực hành tuần 1 môn Lập trình WWW:
![image](https://github.com/ngocmai1522k2/www_lab_week1/assets/144517477/a8411a54-d7b8-4844-8a1d-97a536bc8c6b)


## Mô tả

Dự án Jakarta EE này được phát triển để quản lý tài khoản và phân quyền trong một ứng dụng sử dụng cơ sở dữ liệu Mariadb, sử dụng . Ứng dụng cho phép thêm, cập nhật, xóa tài khoản và quyền, đăng nhập, hiển thị thông tin tài khoản và quyền, cấp quyền cho tài khoản và ghi log đăng nhập và đăng xuất.

## Yêu cầu

- **Java Servlets:** Sử dụng để xây dựng các trang web động và xử lý yêu cầu HTTP.
- **Cơ sở dữ liệu MySQL:** Dùng để lưu trữ thông tin tài khoản, quyền truy cập và log.
- **Servlet API:** API cho Servlets, cung cấp các lớp và giao diện để phát triển ứng dụng web.
- **JDBC:** Java Database Connectivity, sử dụng để kết nối và tương tác với cơ sở dữ liệu MySQL.

## Cách cài đặt

1. **Clone dự án từ GitHub hoặc GitLab:**

```
git clone [https://github.com/yourusername/week01_lab_HotenSv_mssv.git](https://github.com/ngocmai1522k2/www_lab_week1.git)
```

2. **Mở dự án bằng một trình biên soạn mã Java:** IntelliJ IDEA.

3. **Cấu hình Client driver cho MariaDB trong file build.gradle:**
 ```implementation 'org.mariadb.jdbc:mariadb-java-client:3.2.0' ```
4. **Kết nối database Mariadb:**
   ### Database script: 
![image](https://github.com/ngocmai1522k2/www_lab_week1/assets/144517477/692815b4-8a2c-4fe0-add3-8c86310ead92)
![image](https://github.com/ngocmai1522k2/www_lab_week1/assets/144517477/d9076011-6038-4263-86a8-5b1fee53562a)

6. **Cấu trúc project:**
  - **models:** Package này chứa các đối tượng cơ bản của dự án như Account, Grant, GrantAccess, Logs và Role.

  - **connection:** Package này bao gồm lớp ConnectDB, dùng để kết nối đến cơ sở dữ liệu.

  - **Repositories:** Các package con sau đây chứa các lớp Repository để thực hiện các thao tác truy vấn và cập nhật dữ liệu trong cơ sở dữ liệu:
      + **AccountRepositories:** Chứa các hàm login, thêm, tìm tài khoản theo ID, tìm tất cả các tài khoản, xóa, cập nhật,  đăng xuất và lấy danh sách tài khoản theo quyền.
      + **GrantAccessRepositories:** Chứa hàm để lấy quyền của tài khoản đăng nhập, thêm quyền, kiểm tra tài khoản được cấp quyền chưa, cập nhật quyền, 
      + **LogRepositories:** Chứa hàm ghi log sự kiện.
      + **RoleRepositories:** Chứa hàm để lấy danh sách quyền của mỗi tài khoản, danh sách tất cả quyền

  - **Controllers:** Chứa ControllerServlet để xử lý yêu cầu đăng nhập và các yêu cầu khác.

7. **Chạy ứng dụng:** Khởi động máy chủ servlet và truy cập ứng dụng qua trình duyệt web bằng địa chỉ URL tương ứng (ví dụ: `[http://localhost:8080/lab1/](http://localhost:8080/Gradle___vn_edu_iuh_fit___week1_lab_LeThiNgocMai_20005501_1_0_SNAPSHOT_war/)`).

## Cách sử dụng

1. Chạy project bằng Tomcat Server Tomcat 10.1.12

2. Trang đăng nhập sẽ hiển thị. Nhập thông tin đăng nhập để tiếp tục.
  ![image](https://github.com/ngocmai1522k2/www_lab_week1/assets/144517477/8a8c25e9-8d97-4e54-ae67-b21531d2475f)

3. Đăng nhập tài khoản teo@gmail.com vai trò admin:
   
  ![image](https://github.com/ngocmai1522k2/www_lab_week1/assets/144517477/d4a9cd7c-9770-4344-9e02-3278f51e8ed1)

5. Sau khi đăng nhập thành công, bạn sẽ được định hướng đến trang chủ hiển thị thông tin account của bạn, tùy thuộc vào quyền của tài khoản. Nếu bạn là admin, bạn có quyền quản lý tài khoản và quyền. Ngược lại, bạn sẽ thấy thông tin của mình và các quyền mà bạn có.
  ![image](https://github.com/ngocmai1522k2/www_lab_week1/assets/144517477/95f533a5-59e9-44c2-bb70-3a3555988191)
   Đăng nhập bằng tài khoản met@gmail.com vai trò user:
  ![image](https://github.com/ngocmai1522k2/www_lab_week1/assets/144517477/21df2eef-bb7c-488b-8c98-cdbfa204c1ec)

6. Chức năng quản lý danh sách account sẽ hiển thị thông tin từng account,  hiển thị nút xóa, cập nhật, cấp quyền trên từng dòng:
  ![image](https://github.com/ngocmai1522k2/www_lab_week1/assets/144517477/297d3087-bac9-47fa-a53c-00dcd822ed43)
7. Chức năng xóa account: account xóa sẽ có status là -1 trong database và không còn hiển thị trên danh sách quản lý account:
  ![image](https://github.com/ngocmai1522k2/www_lab_week1/assets/144517477/7cad9909-eea4-4582-ae4e-8d91a5acc650)
8. Chức năng update: khi click vào nút update ở 1 account bất kì, sẽ chuyển sang trang update và hiển thị các thông tin của account cần update, chỉ có thể chỉnh được email, password và phone:
  ![image](https://github.com/ngocmai1522k2/www_lab_week1/assets/144517477/fc8bfd85-8419-4d31-bce4-abff6623cda8)
  ![image](https://github.com/ngocmai1522k2/www_lab_week1/assets/144517477/e8e4f00c-fb27-4ead-9019-e28fb2df62dd)
9. Chức năng list role của account đang đăng nhập:
   ![image](https://github.com/ngocmai1522k2/www_lab_week1/assets/144517477/1ce7099b-e511-4ee7-9cf7-2197efc260d5)
10. Chức năng add account:
    ![image](https://github.com/ngocmai1522k2/www_lab_week1/assets/144517477/f1676ef8-1beb-42e7-a422-d7a88b30b7e7)

## Tài liệu liên quan

- [Java EE Documentation](https://javaee.github.io/javaee-spec/)
- [Java Database Connectivity (JDBC) Documentation](https://docs.oracle.com/en/java/javase/16/docs/api/java.sql/java/sql/package-summary.html)
- [Apache Tomcat Documentation](https://tomcat.apache.org/tomcat-9.0-doc/index.html)
- [Git Version Control](https://git-scm.com/book/en/v2)


## Đóng góp

Nếu bạn muốn đóng góp vào dự án hoặc báo cáo lỗi, vui lòng tạo issue hoặc gửi pull request vào repository GitHub của dự án.

- GitHub Repository: [www_lab_week1](https://github.com/ngocmai1522k2/www_lab_week1)
- Tạo issue mới: [Tạo issue](https://github.com/ngocmai1522k2/www_lab_week1/issues/new)
- Gửi pull request: [Gửi pull request](https://github.com/ngocmai1522k2/www_lab_week1/compare)

Chúng tôi rất hoan nghênh mọi đóng góp từ cộng đồng!

---
