package cn.mldn.fastdfs.util;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.ProtoCommon;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;

public class FastDFSUtil {
	public static String upload(MultipartFile file) {
		try {
			ClassPathResource classPathResource = new ClassPathResource("config/fastdfs.properties") ;
			String url = classPathResource.getURI().toString() ;
			ClientGlobal.init(url.replaceAll("file:",""));
			TrackerClient trackerClient = new TrackerClient() ;
			TrackerServer trackerServer = trackerClient.getConnection() ;
			StorageServer storageServer = null ;
			StorageClient storageClient = new StorageClient(trackerServer,storageServer) ;
			NameValuePair[] metaList = new NameValuePair[3] ;
			metaList[0] = new NameValuePair("fileName",file.getOriginalFilename()) ;
			metaList[1] = new NameValuePair("fileExtName",file.getOriginalFilename().substring(file.getOriginalFilename().indexOf(".") + 1)) ;
			metaList[2] = new NameValuePair("fileLength",String.valueOf(file.getSize())) ;
			String[] upload_file = storageClient.upload_file(file.getBytes(), file.getOriginalFilename().substring(file.getOriginalFilename().indexOf(".") + 1), metaList) ;
			trackerServer.close();
			return upload_file[0] + "/" + upload_file[1] ;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null ;
	}
	public static String getPhotoPath(String photo) {
		try {
			ClassPathResource classPathResource = new ClassPathResource("fastdfs.properties");
			ClientGlobal.init(classPathResource.getClassLoader().getResource("fastdfs.properties").getPath());
			TrackerClient trackerClient = new TrackerClient();
			TrackerServer trackerServer = trackerClient.getConnection();
			String temp[] = photo.split("/",2) ;
			String fileUrl = temp[1];
			int ts = (int) (System.currentTimeMillis() / 1000);
			StringBuffer accessUrl = new StringBuffer();
			accessUrl.append("http://");
			accessUrl.append(trackerServer.getInetSocketAddress().getHostString());
			accessUrl.append("/" + temp[0] + "/").append(fileUrl);
			accessUrl.append("?token=").append(ProtoCommon.getToken(fileUrl, ts, ClientGlobal.g_secret_key));
			accessUrl.append("&ts=").append(ts);
			System.out.println(accessUrl);
			trackerServer.close();
			return accessUrl.toString() ;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null ;
	}
}
