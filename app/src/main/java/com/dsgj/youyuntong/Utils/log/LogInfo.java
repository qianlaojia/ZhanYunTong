package com.dsgj.youyuntong.Utils.log;

class LogInfo {

	private String content;
	private String fileName;

	/**
	 * 
	 * @param content
	 *            内容
	 * @param fileName
	 *            文件名
	 */
	public LogInfo(String content, String fileName) {
		this.content = content;
		this.fileName = fileName;
	}

	/**
	 * 内容
	 * 
	 * @return  显示的内容
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 获取文件名
	 * 
	 * @return  文件名
	 */
	public String getFileName() {
		return fileName;
	}
}
