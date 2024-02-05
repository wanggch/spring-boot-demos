package cn.ddcherry.springboot.demo.service;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
@Service
public class BackupService {

	private String username = "root";
	private String password = "abc123";
	// 锁，防止并发备份
	private Lock lock = new ReentrantLock();
	// 日期格式化
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
	private static final String BACKUP_FILE_PATTERN = "precise_jktj_{}.sql";
	private static final List<String> TABLES = Arrays.asList("");
	private static final String BACKUP_SQL_PATH = "";

	public Object backup() {

		LocalDateTime now = LocalDateTime.now();
		Path dir = Paths.get(BACKUP_SQL_PATH);

		String backupFileName = StrUtil.format(BACKUP_FILE_PATTERN, now.format(formatter));
		// 备份的SQL文件
		Path sqlFile = dir.resolve(backupFileName);
		try {
			// 创建备份文件文件
			Files.createFile(sqlFile);

			// 构建备份命令
			ProcessBuilder processBuilder = new ProcessBuilder(
				"mysqldump",
				"-u" + this.username,
				"-p" + this.password,
				"precise_jktj");

			for (String table : TABLES) {
				processBuilder.command().add(table);
			}

			// 设置重定向输出到文件
			processBuilder.redirectOutput(ProcessBuilder.Redirect.to(new java.io.File(BACKUP_SQL_PATH, backupFileName)));

			// 执行备份命令
			Process process = processBuilder.start();
			int code = process.waitFor();
			System.out.println("exitCode: " + code);
			if (Objects.equals(code, 0)) {
				return sqlFile.getFileName().toString();
			} else {
				log.error("备份失败，错误码：{}", code);
				return null;
			}
		} catch (Exception e) {
			log.error("备份数据库时发生错误，异常信息：", e);
			FileUtil.del(sqlFile);
			return null;
		}
	}
}
