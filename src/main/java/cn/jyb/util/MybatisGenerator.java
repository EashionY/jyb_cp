package cn.jyb.util;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.api.ProgressCallback;
import org.mybatis.generator.api.VerboseProgressCallback;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;
/**
 * mybatis generator代码自动生成工具
 * @author Eashion
 *
 */
public class MybatisGenerator {

	public static void main(String[] args) throws IOException, XMLParserException, InvalidConfigurationException, SQLException, InterruptedException{
	   List<String> warnings = new ArrayList<String>();
	   boolean overwrite = true;
	   File configFile = new File("src/main/resources/generatorConfig.xml");
	   ConfigurationParser cp = new ConfigurationParser(warnings);
	   Configuration config = cp.parseConfiguration(configFile);
	   DefaultShellCallback callback = new DefaultShellCallback(overwrite);
	   MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
	   ProgressCallback progressCallback = new VerboseProgressCallback();
//	   myBatisGenerator.generate(null);
	   myBatisGenerator.generate(progressCallback);
	   System.out.println("执行完毕");
	}
	
	
}
