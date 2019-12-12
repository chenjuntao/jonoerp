package action.setting;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 将文件打包成ZIP压缩文件
 * 
 * @author czc
 * @since 2015-6-11
 */

public final class FileToZip {

	private FileToZip() {

	}

	/**
	 * 将存放在sourceFilePath目录下的源文件,打包成fileName名称的ZIP文件,并存放到zipFilePath。
	 * 
	 * @param sourceFilePath
	 *            待压缩的文件路径
	 * @param zipFilePath
	 *            压缩后存放路径
	 * @param fileName
	 *            压缩后文件的名称
	 * @return flag lys
	 */

	@SuppressWarnings("resource")
	public static boolean fileToZip(String sourceFilePath, String zipFilePath,
			String fileName) {

		boolean flag = false;

		File sourceFile = new File(sourceFilePath);
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		FileOutputStream fos = null;
		ZipOutputStream zos = null;

		if (sourceFile.exists() == false) {
			System.out.println(">>>>>> 待压缩的文件目录：" + sourceFilePath
					+ " 不存在. <<<<<<");
		} else {
			try {

				File zipFile = new File(zipFilePath + "/" + fileName + ".zip");
				if (!zipFile.exists()) {
					System.out.println(">>>>>> " + zipFilePath + " 目录下存在名字为："
							+ fileName + ".zip" + " 打包文件. <<<<<<");
				} else {
					File[] sourceFiles = sourceFile.listFiles();
					if (null == sourceFiles || sourceFiles.length < 1) {
						System.out.println(">>>>>> 待压缩的文件目录：" + sourceFilePath
								+ " 里面不存在文件,无需压缩. <<<<<<");
					} else {
						fos = new FileOutputStream(zipFile);
						zos = new ZipOutputStream(new BufferedOutputStream(fos));

						byte[] bufs = new byte[1024 * 10];
						for (int i = 0; i < sourceFiles.length; i++) {
							// 创建ZIP实体,并添加进压缩包

							ZipEntry zipEntry = new ZipEntry(
									sourceFiles[i].getName());
							zos.putNextEntry(zipEntry);
							// 读取待压缩的文件并写进压缩包里
							fis = new FileInputStream(sourceFiles[i]);
							bis = new BufferedInputStream(fis, 1024 * 10);
							int read = 0;

							while ((read = bis.read(bufs, 0, 1024 * 10)) != -1) {
								zos.write(bufs, 0, read);
							}
						}
						flag = true;
					}

				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
				throw new RuntimeException(e);

			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			} finally {
				try {
					if (null != bis)
						bis.close();
					if (null != zos)
						zos.close();

				} catch (IOException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}

			}

		}
		return flag;
	}

	/**
	 * 压缩文件夹 升级压缩文件的方法
	 * 
	 * @param sourceDIR
	 *            文件夹名称（包含路径）
	 * @param targetZipFile
	 *            生成zip文件名
	 * @author lys
	 * @date 2012 12 26
	 */

	@SuppressWarnings("resource")
	public static void zipDIR(String sourceDIR, String targetZipFile) {

		try {

			FileOutputStream target = new FileOutputStream(targetZipFile);
			ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(
					target));

			int BUFFER_SIZE = 1024;
			byte buff[] = new byte[BUFFER_SIZE];

			File dir = new File(sourceDIR);
			if (!dir.isDirectory()) {
				throw new IllegalArgumentException(sourceDIR
						+ " is not a directory!");
			}

			File files[] = dir.listFiles();
			for (int i = 0; i < files.length; i++) {

				FileInputStream fi = new FileInputStream(files[i]);
				BufferedInputStream origin = new BufferedInputStream(fi);
				ZipEntry entry = new ZipEntry(files[i].getName());
				out.putNextEntry(entry);
				int count;

				while ((count = origin.read(buff)) != -1) {
					out.write(buff, 0, count);
				}
				origin.close();
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

}
