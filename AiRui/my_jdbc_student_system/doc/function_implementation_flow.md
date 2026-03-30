# 学生管理系统功能实现流程

## 1. 登录模块

### 入口方法：MenuController.login()

#### 方法调用链：
MenuController.login() → UserService.login() → UserDAO.findByUsername() → PasswordUtil.checkPassword()

#### 方法说明：
1. **MenuController.login()**
   - 功能：处理用户登录
   - 入参：无
   - 返回：无
   - 工作：获取用户名和密码，调用UserService.login()

2. **UserService.login(String username, String password)**
   - 功能：验证用户登录
   - 入参：username(用户名), password(密码)
   - 返回：User对象（成功）或null（失败）
   - 工作：验证用户信息，检查密码

3. **UserDAO.findByUsername(Connection conn, String username)**
   - 功能：根据用户名查询用户
   - 入参：conn(数据库连接), username(用户名)
   - 返回：User对象（存在）或null（不存在）
   - 工作：执行SQL查询，返回用户对象

4. **PasswordUtil.checkPassword(String plainPassword, String hashedPassword)**
   - 功能：验证密码是否匹配
   - 入参：plainPassword(明文密码), hashedPassword(加密密码)
   - 返回：boolean（是否匹配）
   - 工作：使用BCrypt算法验证密码

## 2. 班级管理模块

### 2.1 添加班级

#### 入口方法：ClassController.addClass()

##### 方法调用链：
ClassController.addClass() → ClassService.addClass() → ClassService.validateClassInfo() → ClassDAO.insert() → BaseDAO.executeInsert()

##### 方法说明：
1. **ClassController.addClass()**
   - 功能：添加班级
   - 入参：无
   - 返回：无
   - 工作：获取用户输入，调用ClassService.addClass()

2. **ClassService.addClass(ClassInfo classInfo)**
   - 功能：添加班级
   - 入参：classInfo(班级信息)
   - 返回：boolean（是否成功）
   - 工作：验证班级信息，调用ClassDAO.insert()

3. **ClassService.validateClassInfo(ClassInfo classInfo, boolean isUpdate)**
   - 功能：验证班级信息
   - 入参：classInfo(班级信息), isUpdate(是否为更新操作)
   - 返回：无
   - 工作：验证班级信息的合法性

4. **ClassDAO.insert(Connection conn, ClassInfo classInfo)**
   - 功能：插入班级
   - 入参：conn(数据库连接), classInfo(班级信息)
   - 返回：Integer（生成的班级ID）
   - 工作：执行SQL插入操作

5. **BaseDAO.executeInsert(Connection conn, String sql, Object... params)**
   - 功能：执行插入操作并返回生成的主键
   - 入参：conn(数据库连接), sql(SQL语句), params(参数数组)
   - 返回：Integer（生成的主键）
   - 工作：执行SQL插入操作，返回生成的主键

### 2.2 修改班级

#### 入口方法：ClassController.updateClass()

##### 方法调用链：
ClassController.updateClass() → ClassService.getClassById() → ClassDAO.findById() → ClassService.updateClass() → ClassService.validateClassInfo() → ClassDAO.update() → BaseDAO.executeUpdate()

##### 方法说明：
1. **ClassController.updateClass()**
   - 功能：修改班级
   - 入参：无
   - 返回：无
   - 工作：获取用户输入，调用ClassService.updateClass()

2. **ClassService.getClassById(Integer classId)**
   - 功能：根据ID获取班级
   - 入参：classId(班级ID)
   - 返回：ClassInfo（班级信息）
   - 工作：调用ClassDAO.findById()

3. **ClassDAO.findById(Connection conn, Integer classId)**
   - 功能：根据ID查询班级
   - 入参：conn(数据库连接), classId(班级ID)
   - 返回：ClassInfo（班级信息）
   - 工作：执行SQL查询，返回班级对象

4. **ClassService.updateClass(ClassInfo classInfo)**
   - 功能：更新班级信息
   - 入参：classInfo(班级信息)
   - 返回：boolean（是否成功）
   - 工作：验证班级信息，调用ClassDAO.update()

5. **ClassDAO.update(Connection conn, ClassInfo classInfo)**
   - 功能：更新班级
   - 入参：conn(数据库连接), classInfo(班级信息)
   - 返回：int（受影响的行数）
   - 工作：执行SQL更新操作

6. **BaseDAO.executeUpdate(Connection conn, String sql, Object... params)**
   - 功能：执行更新操作
   - 入参：conn(数据库连接), sql(SQL语句), params(参数数组)
   - 返回：int（受影响的行数）
   - 工作：执行SQL更新操作

### 2.3 删除班级

#### 入口方法：ClassController.deleteClass()

##### 方法调用链：
ClassController.deleteClass() → ClassService.getClassById() → ClassDAO.findById() → ClassService.deleteClass() → ClassDAO.delete() → BaseDAO.executeUpdate()

##### 方法说明：
1. **ClassController.deleteClass()**
   - 功能：删除班级
   - 入参：无
   - 返回：无
   - 工作：获取用户输入，调用ClassService.deleteClass()

2. **ClassService.getClassById(Integer classId)**
   - 功能：根据ID获取班级
   - 入参：classId(班级ID)
   - 返回：ClassInfo（班级信息）
   - 工作：调用ClassDAO.findById()

3. **ClassDAO.findById(Connection conn, Integer classId)**
   - 功能：根据ID查询班级
   - 入参：conn(数据库连接), classId(班级ID)
   - 返回：ClassInfo（班级信息）
   - 工作：执行SQL查询，返回班级对象

4. **ClassService.deleteClass(Integer classId)**
   - 功能：删除班级
   - 入参：classId(班级ID)
   - 返回：boolean（是否成功）
   - 工作：调用ClassDAO.delete()

5. **ClassDAO.delete(Connection conn, Integer classId)**
   - 功能：删除班级
   - 入参：conn(数据库连接), classId(班级ID)
   - 返回：int（受影响的行数）
   - 工作：执行SQL删除操作

### 2.4 查询班级

#### 入口方法：ClassController.queryMenu()

##### 方法调用链：
ClassController.queryMenu() → ClassController.listAllClasses() → ClassService.getAllClasses() → ClassDAO.findAll() → BaseDAO.queryList()

##### 方法说明：
1. **ClassController.queryMenu()**
   - 功能：显示班级查询菜单
   - 入参：无
   - 返回：无
   - 工作：显示查询菜单，获取用户选择

2. **ClassController.listAllClasses()**
   - 功能：显示所有班级
   - 入参：无
   - 返回：无
   - 工作：调用ClassService.getAllClasses()，显示班级列表

3. **ClassService.getAllClasses()**
   - 功能：获取所有班级
   - 入参：无
   - 返回：List<ClassInfo>（班级列表）
   - 工作：调用ClassDAO.findAll()

4. **ClassDAO.findAll(Connection conn)**
   - 功能：查询所有班级
   - 入参：conn(数据库连接)
   - 返回：List<ClassInfo>（班级列表）
   - 工作：执行SQL查询，返回班级列表

5. **BaseDAO.queryList(Connection conn, String sql, RowMapper<T> rowMapper, Object... params)**
   - 功能：查询对象列表
   - 入参：conn(数据库连接), sql(SQL语句), rowMapper(行映射器), params(参数数组)
   - 返回：List<T>（对象列表）
   - 工作：执行SQL查询，映射结果集

## 3. 学生管理模块

### 3.1 添加学生

#### 入口方法：StudentController.addStudent()

##### 方法调用链：
StudentController.addStudent() → StudentService.addStudent() → StudentService.validateStudent() → StudentDAO.existsByStudentNo() → BaseDAO.queryCount() → StudentDAO.insert() → BaseDAO.executeInsert()

##### 方法说明：
1. **StudentController.addStudent()**
   - 功能：添加学生
   - 入参：无
   - 返回：无
   - 工作：获取用户输入，调用StudentService.addStudent()

2. **StudentService.addStudent(Student student)**
   - 功能：添加学生
   - 入参：student(学生信息)
   - 返回：boolean（是否成功）
   - 工作：验证学生信息，检查学号是否存在，调用StudentDAO.insert()

3. **StudentService.validateStudent(Student student, boolean isUpdate)**
   - 功能：验证学生信息
   - 入参：student(学生信息), isUpdate(是否为更新操作)
   - 返回：无
   - 工作：验证学生信息的合法性

4. **StudentDAO.existsByStudentNo(Connection conn, String studentNo)**
   - 功能：检查学号是否存在
   - 入参：conn(数据库连接), studentNo(学号)
   - 返回：boolean（是否存在）
   - 工作：执行SQL查询，检查学号是否存在

5. **BaseDAO.queryCount(Connection conn, String sql, Object... params)**
   - 功能：查询记录数量
   - 入参：conn(数据库连接), sql(SQL语句), params(参数数组)
   - 返回：int（记录数量）
   - 工作：执行SQL查询，返回记录数量

6. **StudentDAO.insert(Connection conn, Student student)**
   - 功能：插入学生
   - 入参：conn(数据库连接), student(学生信息)
   - 返回：Integer（生成的学生ID）
   - 工作：执行SQL插入操作

### 3.2 修改学生

#### 入口方法：StudentController.updateStudent()

##### 方法调用链：
StudentController.updateStudent() → StudentService.getStudentByNo() → StudentDAO.findByStudentNo() → StudentService.updateStudent() → StudentService.validateStudent() → StudentDAO.existsByStudentNoExcludeId() → StudentDAO.update() → BaseDAO.executeUpdate()

##### 方法说明：
1. **StudentController.updateStudent()**
   - 功能：修改学生
   - 入参：无
   - 返回：无
   - 工作：获取用户输入，调用StudentService.updateStudent()

2. **StudentService.getStudentByNo(String studentNo)**
   - 功能：根据学号获取学生
   - 入参：studentNo(学号)
   - 返回：Student（学生信息）
   - 工作：调用StudentDAO.findByStudentNo()

3. **StudentDAO.findByStudentNo(Connection conn, String studentNo)**
   - 功能：根据学号查询学生
   - 入参：conn(数据库连接), studentNo(学号)
   - 返回：Student（学生信息）
   - 工作：执行SQL查询，返回学生对象

4. **StudentService.updateStudent(Student student)**
   - 功能：更新学生信息
   - 入参：student(学生信息)
   - 返回：boolean（是否成功）
   - 工作：验证学生信息，检查学号是否存在（排除当前学生），调用StudentDAO.update()

5. **StudentDAO.existsByStudentNoExcludeId(Connection conn, String studentNo, Integer excludeId)**
   - 功能：检查学号是否存在（排除指定ID）
   - 入参：conn(数据库连接), studentNo(学号), excludeId(排除的学生ID)
   - 返回：boolean（是否存在）
   - 工作：执行SQL查询，检查学号是否存在

6. **StudentDAO.update(Connection conn, Student student)**
   - 功能：更新学生
   - 入参：conn(数据库连接), student(学生信息)
   - 返回：int（受影响的行数）
   - 工作：执行SQL更新操作

### 3.3 删除学生

#### 入口方法：StudentController.deleteStudent()

##### 方法调用链：
StudentController.deleteStudent() → StudentService.getStudentByNo() → StudentDAO.findByStudentNo() → StudentService.deleteStudent() → StudentDAO.delete() → BaseDAO.executeUpdate()

##### 方法说明：
1. **StudentController.deleteStudent()**
   - 功能：删除学生
   - 入参：无
   - 返回：无
   - 工作：获取用户输入，调用StudentService.deleteStudent()

2. **StudentService.getStudentByNo(String studentNo)**
   - 功能：根据学号获取学生
   - 入参：studentNo(学号)
   - 返回：Student（学生信息）
   - 工作：调用StudentDAO.findByStudentNo()

3. **StudentDAO.findByStudentNo(Connection conn, String studentNo)**
   - 功能：根据学号查询学生
   - 入参：conn(数据库连接), studentNo(学号)
   - 返回：Student（学生信息）
   - 工作：执行SQL查询，返回学生对象

4. **StudentService.deleteStudent(Integer studentId)**
   - 功能：删除学生
   - 入参：studentId(学生ID)
   - 返回：boolean（是否成功）
   - 工作：调用StudentDAO.delete()

5. **StudentDAO.delete(Connection conn, Integer studentId)**
   - 功能：删除学生
   - 入参：conn(数据库连接), studentId(学生ID)
   - 返回：int（受影响的行数）
   - 工作：执行SQL删除操作

### 3.4 查询学生

#### 入口方法：StudentController.queryMenu()

##### 方法调用链：
StudentController.queryMenu() → StudentController.listAllStudents() → StudentService.getAllStudents() → StudentDAO.findAll() → BaseDAO.queryList()

##### 方法说明：
1. **StudentController.queryMenu()**
   - 功能：显示学生查询菜单
   - 入参：无
   - 返回：无
   - 工作：显示查询菜单，获取用户选择

2. **StudentController.listAllStudents()**
   - 功能：显示所有学生
   - 入参：无
   - 返回：无
   - 工作：调用StudentService.getAllStudents()，显示学生列表

3. **StudentService.getAllStudents()**
   - 功能：获取所有学生
   - 入参：无
   - 返回：List<Student>（学生列表）
   - 工作：调用StudentDAO.findAll()

4. **StudentDAO.findAll(Connection conn)**
   - 功能：查询所有学生
   - 入参：conn(数据库连接)
   - 返回：List<Student>（学生列表）
   - 工作：执行SQL查询，返回学生列表

## 4. 课程管理模块

### 4.1 添加课程

#### 入口方法：CourseController.addCourse()

##### 方法调用链：
CourseController.addCourse() → CourseService.addCourse() → CourseService.validateCourse() → CourseDAO.existsByCourseNo() → BaseDAO.queryCount() → CourseDAO.insert() → BaseDAO.executeInsert()

##### 方法说明：
1. **CourseController.addCourse()**
   - 功能：添加课程
   - 入参：无
   - 返回：无
   - 工作：获取用户输入，调用CourseService.addCourse()

2. **CourseService.addCourse(Course course)**
   - 功能：添加课程
   - 入参：course(课程信息)
   - 返回：boolean（是否成功）
   - 工作：验证课程信息，检查课程编号是否存在，调用CourseDAO.insert()

3. **CourseService.validateCourse(Course course, boolean isUpdate)**
   - 功能：验证课程信息
   - 入参：course(课程信息), isUpdate(是否为更新操作)
   - 返回：无
   - 工作：验证课程信息的合法性

4. **CourseDAO.existsByCourseNo(Connection conn, String courseNo)**
   - 功能：检查课程编号是否存在
   - 入参：conn(数据库连接), courseNo(课程编号)
   - 返回：boolean（是否存在）
   - 工作：执行SQL查询，检查课程编号是否存在

5. **CourseDAO.insert(Connection conn, Course course)**
   - 功能：插入课程
   - 入参：conn(数据库连接), course(课程信息)
   - 返回：Integer（生成的课程ID）
   - 工作：执行SQL插入操作

### 4.2 修改课程

#### 入口方法：CourseController.updateCourse()

##### 方法调用链：
CourseController.updateCourse() → CourseService.getCourseByNo() → CourseDAO.findByCourseNo() → CourseService.updateCourse() → CourseService.validateCourse() → CourseDAO.existsByCourseNoExcludeId() → CourseDAO.update() → BaseDAO.executeUpdate()

##### 方法说明：
1. **CourseController.updateCourse()**
   - 功能：修改课程
   - 入参：无
   - 返回：无
   - 工作：获取用户输入，调用CourseService.updateCourse()

2. **CourseService.getCourseByNo(String courseNo)**
   - 功能：根据课程编号获取课程
   - 入参：courseNo(课程编号)
   - 返回：Course（课程信息）
   - 工作：调用CourseDAO.findByCourseNo()

3. **CourseDAO.findByCourseNo(Connection conn, String courseNo)**
   - 功能：根据课程编号查询课程
   - 入参：conn(数据库连接), courseNo(课程编号)
   - 返回：Course（课程信息）
   - 工作：执行SQL查询，返回课程对象

4. **CourseService.updateCourse(Course course)**
   - 功能：更新课程信息
   - 入参：course(课程信息)
   - 返回：boolean（是否成功）
   - 工作：验证课程信息，检查课程编号是否存在（排除当前课程），调用CourseDAO.update()

5. **CourseDAO.existsByCourseNoExcludeId(Connection conn, String courseNo, Integer excludeId)**
   - 功能：检查课程编号是否存在（排除指定ID）
   - 入参：conn(数据库连接), courseNo(课程编号), excludeId(排除的课程ID)
   - 返回：boolean（是否存在）
   - 工作：执行SQL查询，检查课程编号是否存在

6. **CourseDAO.update(Connection conn, Course course)**
   - 功能：更新课程
   - 入参：conn(数据库连接), course(课程信息)
   - 返回：int（受影响的行数）
   - 工作：执行SQL更新操作

### 4.3 删除课程

#### 入口方法：CourseController.deleteCourse()

##### 方法调用链：
CourseController.deleteCourse() → CourseService.getCourseByNo() → CourseDAO.findByCourseNo() → CourseService.deleteCourse() → CourseDAO.delete() → BaseDAO.executeUpdate()

##### 方法说明：
1. **CourseController.deleteCourse()**
   - 功能：删除课程
   - 入参：无
   - 返回：无
   - 工作：获取用户输入，调用CourseService.deleteCourse()

2. **CourseService.getCourseByNo(String courseNo)**
   - 功能：根据课程编号获取课程
   - 入参：courseNo(课程编号)
   - 返回：Course（课程信息）
   - 工作：调用CourseDAO.findByCourseNo()

3. **CourseDAO.findByCourseNo(Connection conn, String courseNo)**
   - 功能：根据课程编号查询课程
   - 入参：conn(数据库连接), courseNo(课程编号)
   - 返回：Course（课程信息）
   - 工作：执行SQL查询，返回课程对象

4. **CourseService.deleteCourse(Integer courseId)**
   - 功能：删除课程
   - 入参：courseId(课程ID)
   - 返回：boolean（是否成功）
   - 工作：调用CourseDAO.delete()

5. **CourseDAO.delete(Connection conn, Integer courseId)**
   - 功能：删除课程
   - 入参：conn(数据库连接), courseId(课程ID)
   - 返回：int（受影响的行数）
   - 工作：执行SQL删除操作

### 4.4 查询课程

#### 入口方法：CourseController.queryMenu()

##### 方法调用链：
CourseController.queryMenu() → CourseController.listAllCourses() → CourseService.getAllCourses() → CourseDAO.findAll() → BaseDAO.queryList()

##### 方法说明：
1. **CourseController.queryMenu()**
   - 功能：显示课程查询菜单
   - 入参：无
   - 返回：无
   - 工作：显示查询菜单，获取用户选择

2. **CourseController.listAllCourses()**
   - 功能：显示所有课程
   - 入参：无
   - 返回：无
   - 工作：调用CourseService.getAllCourses()，显示课程列表

3. **CourseService.getAllCourses()**
   - 功能：获取所有课程
   - 入参：无
   - 返回：List<Course>（课程列表）
   - 工作：调用CourseDAO.findAll()

4. **CourseDAO.findAll(Connection conn)**
   - 功能：查询所有课程
   - 入参：conn(数据库连接)
   - 返回：List<Course>（课程列表）
   - 工作：执行SQL查询，返回课程列表

## 5. 成绩管理模块

### 5.1 成绩管理菜单

#### 入口方法：ScoreController.menu()

##### 方法调用链：
ScoreController.menu()

##### 方法说明：
1. **ScoreController.menu()**
   - 功能：显示成绩管理菜单
   - 入参：无
   - 返回：无
   - 工作：显示成绩管理菜单，获取用户选择

### 5.2 查询个人成绩

#### 入口方法：ScoreController.queryOwnScores()

##### 方法调用链：
ScoreController.queryOwnScores()

##### 方法说明：
1. **ScoreController.queryOwnScores()**
   - 功能：查询个人成绩
   - 入参：无
   - 返回：无
   - 工作：显示个人成绩查询界面

## 6. 用户管理模块

### 6.1 添加用户

#### 入口方法：UserController.addUser()

##### 方法调用链：
UserController.addUser() → UserService.addUser() → UserService.validateUser() → UserDAO.existsByUsername() → BaseDAO.queryCount() → PasswordUtil.hashPassword() → UserDAO.insert() → BaseDAO.executeInsert()

##### 方法说明：
1. **UserController.addUser()**
   - 功能：添加用户
   - 入参：无
   - 返回：无
   - 工作：获取用户输入，调用UserService.addUser()

2. **UserService.addUser(User user, String plainPassword)**
   - 功能：添加用户
   - 入参：user(用户对象), plainPassword(明文密码)
   - 返回：boolean（是否成功）
   - 工作：验证用户信息，检查用户名是否存在，加密密码，调用UserDAO.insert()

3. **UserService.validateUser(User user, boolean isUpdate)**
   - 功能：验证用户信息
   - 入参：user(用户对象), isUpdate(是否为更新操作)
   - 返回：无
   - 工作：验证用户信息的合法性

4. **UserDAO.existsByUsername(Connection conn, String username)**
   - 功能：检查用户名是否存在
   - 入参：conn(数据库连接), username(用户名)
   - 返回：boolean（是否存在）
   - 工作：执行SQL查询，检查用户名是否存在

5. **PasswordUtil.hashPassword(String plainPassword)**
   - 功能：加密密码
   - 入参：plainPassword(明文密码)
   - 返回：String（加密后的密码）
   - 工作：使用BCrypt算法加密密码

6. **UserDAO.insert(Connection conn, User user)**
   - 功能：插入用户
   - 入参：conn(数据库连接), user(用户对象)
   - 返回：Integer（生成的用户ID）
   - 工作：执行SQL插入操作

### 6.2 修改用户

#### 入口方法：UserController.updateUser()

##### 方法调用链：
UserController.updateUser() → UserService.getUserById() → UserDAO.findById() → UserService.updateUser() → UserService.validateUser() → UserDAO.existsByUsernameExcludeId() → UserDAO.update() → BaseDAO.executeUpdate()

##### 方法说明：
1. **UserController.updateUser()**
   - 功能：修改用户
   - 入参：无
   - 返回：无
   - 工作：获取用户输入，调用UserService.updateUser()

2. **UserService.getUserById(Integer userId)**
   - 功能：根据ID获取用户
   - 入参：userId(用户ID)
   - 返回：User（用户信息）
   - 工作：调用UserDAO.findById()

3. **UserDAO.findById(Connection conn, Integer userId)**
   - 功能：根据ID查询用户
   - 入参：conn(数据库连接), userId(用户ID)
   - 返回：User（用户信息）
   - 工作：执行SQL查询，返回用户对象

4. **UserService.updateUser(User user)**
   - 功能：更新用户信息
   - 入参：user(用户信息)
   - 返回：boolean（是否成功）
   - 工作：验证用户信息，检查用户名是否存在（排除当前用户），调用UserDAO.update()

5. **UserDAO.existsByUsernameExcludeId(Connection conn, String username, Integer excludeId)**
   - 功能：检查用户名是否存在（排除指定ID）
   - 入参：conn(数据库连接), username(用户名), excludeId(排除的用户ID)
   - 返回：boolean（是否存在）
   - 工作：执行SQL查询，检查用户名是否存在

6. **UserDAO.update(Connection conn, User user)**
   - 功能：更新用户
   - 入参：conn(数据库连接), user(用户信息)
   - 返回：int（受影响的行数）
   - 工作：执行SQL更新操作

### 6.3 删除用户

#### 入口方法：UserController.deleteUser()

##### 方法调用链：
UserController.deleteUser() → UserService.getUserById() → UserDAO.findById() → UserService.deleteUser() → UserDAO.delete() → BaseDAO.executeUpdate()

##### 方法说明：
1. **UserController.deleteUser()**
   - 功能：删除用户
   - 入参：无
   - 返回：无
   - 工作：获取用户输入，调用UserService.deleteUser()

2. **UserService.getUserById(Integer userId)**
   - 功能：根据ID获取用户
   - 入参：userId(用户ID)
   - 返回：User（用户信息）
   - 工作：调用UserDAO.findById()

3. **UserDAO.findById(Connection conn, Integer userId)**
   - 功能：根据ID查询用户
   - 入参：conn(数据库连接), userId(用户ID)
   - 返回：User（用户信息）
   - 工作：执行SQL查询，返回用户对象

4. **UserService.deleteUser(Integer userId)**
   - 功能：删除用户
   - 入参：userId(用户ID)
   - 返回：boolean（是否成功）
   - 工作：调用UserDAO.delete()

5. **UserDAO.delete(Connection conn, Integer userId)**
   - 功能：删除用户
   - 入参：conn(数据库连接), userId(用户ID)
   - 返回：int（受影响的行数）
   - 工作：执行SQL删除操作

### 6.4 查询用户

#### 入口方法：UserController.queryMenu()

##### 方法调用链：
UserController.queryMenu() → UserController.listAllUsers() → UserService.getAllUsers() → UserDAO.findAll() → BaseDAO.queryList()

##### 方法说明：
1. **UserController.queryMenu()**
   - 功能：显示用户查询菜单
   - 入参：无
   - 返回：无
   - 工作：显示查询菜单，获取用户选择

2. **UserController.listAllUsers()**
   - 功能：显示所有用户
   - 入参：无
   - 返回：无
   - 工作：调用UserService.getAllUsers()，显示用户列表

3. **UserService.getAllUsers()**
   - 功能：获取所有用户
   - 入参：无
   - 返回：List<User>（用户列表）
   - 工作：调用UserDAO.findAll()

4. **UserDAO.findAll(Connection conn)**
   - 功能：查询所有用户
   - 入参：conn(数据库连接)
   - 返回：List<User>（用户列表）
   - 工作：执行SQL查询，返回用户列表

## 7. 修改密码模块

### 入口方法：MenuController.changePassword()

#### 方法调用链：
MenuController.changePassword() → UserService.updatePassword() → UserService.getUserById() → UserDAO.findById() → PasswordUtil.checkPassword() → PasswordUtil.hashPassword() → UserDAO.updatePassword() → BaseDAO.executeUpdate()

#### 方法说明：
1. **MenuController.changePassword()**
   - 功能：修改当前用户密码
   - 入参：无
   - 返回：无
   - 工作：获取用户输入，调用UserService.updatePassword()

2. **UserService.updatePassword(Integer userId, String oldPassword, String newPassword)**
   - 功能：更新密码
   - 入参：userId(用户ID), oldPassword(旧密码), newPassword(新密码)
   - 返回：boolean（是否成功）
   - 工作：验证旧密码，加密新密码，调用UserDAO.updatePassword()

3. **UserService.getUserById(Integer userId)**
   - 功能：根据ID获取用户
   - 入参：userId(用户ID)
   - 返回：User（用户信息）
   - 工作：调用UserDAO.findById()

4. **UserDAO.findById(Connection conn, Integer userId)**
   - 功能：根据ID查询用户
   - 入参：conn(数据库连接), userId(用户ID)
   - 返回：User（用户信息）
   - 工作：执行SQL查询，返回用户对象

5. **PasswordUtil.checkPassword(String plainPassword, String hashedPassword)**
   - 功能：验证密码是否匹配
   - 入参：plainPassword(明文密码), hashedPassword(加密密码)
   - 返回：boolean（是否匹配）
   - 工作：使用BCrypt算法验证密码

6. **PasswordUtil.hashPassword(String plainPassword)**
   - 功能：加密密码
   - 入参：plainPassword(明文密码)
   - 返回：String（加密后的密码）
   - 工作：使用BCrypt算法加密密码

7. **UserDAO.updatePassword(Connection conn, Integer userId, String password)**
   - 功能：更新密码
   - 入参：conn(数据库连接), userId(用户ID), password(加密后的密码)
   - 返回：int（受影响的行数）
   - 工作：执行SQL更新操作
