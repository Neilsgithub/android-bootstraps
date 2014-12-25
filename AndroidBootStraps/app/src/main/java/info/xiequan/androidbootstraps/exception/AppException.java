package info.xiequan.androidbootstraps.exception;

/**
 * Created by spark on 18/9/14.
 * www.blueowls.net
 * i@xiequan.info
 */
public class AppException extends Exception {
    public AppException(){
        super();
    }
    public AppException(String detailMessaage){
        super(detailMessaage);
    }
}
