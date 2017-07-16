import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.agile.service.api.ISysGeneratorService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring.xml" })
public class SysGeneratorServiceTest {
	
	@Autowired
	private ISysGeneratorService sysGeneratorService;
	
	//zip输出路径
	String zipPath = "E://hscar-code//code.zip";
	
	//表名
//	String[] tableNames = new String[] {"tb_token", "tb_user", "tb_driver"};
	String[] tableNames = new String[] {"tb_carpooling_orders","tb_route_detail", "tb_token", "tb_user","tb_driver"};

	@Test
	public void testGeneratorCode() throws IOException {
		byte[] data = sysGeneratorService.generatorCode(tableNames);
		File file = new File(zipPath);
		if(!file.getParentFile().exists()){
			file.getParentFile().mkdirs();
		}
		FileUtils.writeByteArrayToFile(file, data);
		
		//解压zip
		ZipInputStream zip = new ZipInputStream(new FileInputStream(zipPath));
		ZipEntry entry;
		while((entry = zip.getNextEntry()) != null) {
			file = new File("E://hscar-code//" + entry.getName());
			if(!file.getParentFile().exists()){
				file.getParentFile().mkdirs();
			}
			FileOutputStream out = new FileOutputStream(file);
			IOUtils.copy(zip, out);
		}
		zip.close();
	}
}