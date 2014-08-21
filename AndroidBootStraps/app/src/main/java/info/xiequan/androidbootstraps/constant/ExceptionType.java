package info.xiequan.androidbootstraps.constant;

/**
 * Created by Wilson on 14-7-24.
 */
public enum ExceptionType {

    WITHOUT_NETWORK_EXCEPTION("WITHOUT_NETWORK_EXCEPTION"),
    HANDLE_GSON_EXCEPTION("HANDLE_GSON_EXCEPTION"),
    HANDLE_VOLLEY_EXCEPTION("HANDLE_VOLLEY_EXCEPTION"),
    WEB_DATA_EXCEPTION("WEB_DATA_EXCEPTION");

    private String typeName;

    ExceptionType(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }

}
