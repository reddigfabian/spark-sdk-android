package io.particle.android.sdk.cloud;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

import io.particle.android.sdk.cloud.Responses.Models.CoreInfo;

/**
 * All API responses, collected together in one outer class for simplicity's sake.
 */
public class Responses {

    /**
     * ...and to go with the responses, a series of model objects only
     * used internally when dealing with the REST API, never returned
     * outside of the cloudapi package.
     */
    static class Models {


        public static class CoreInfo {

            @SerializedName("last_app")
            public final String lastApp;

            @SerializedName("last_heard")
            public final String lastHeard;

            public final boolean connected;

            public final String deviceId;

            public CoreInfo(String lastApp, String lastHeard, boolean connected, String deviceId) {
                this.lastApp = lastApp;
                this.lastHeard = lastHeard;
                this.connected = connected;
                this.deviceId = deviceId;
            }
        }


        /**
         * Represents a single Particle device in the list returned
         * by a call to "GET /v1/devices"
         */
        public static class SimpleDevice {

            public final String id;

            public final String name;

            @SerializedName("connected")
            public final boolean isConnected;

            @SerializedName("product_id")
            public final int productId;

            public SimpleDevice(String id, String name, boolean isConnected, int productId) {
                this.id = id;
                this.name = name;
                this.isConnected = isConnected;
                this.productId = productId;
            }
        }

        /**
         * Represents a single Particle device as returned from the
         * call to "GET /v1/devices/{device id}"
         */
        class CompleteDevice {
            @SerializedName("id")
            public final String deviceId;

            public final String name;

            @SerializedName("connected")
            public final boolean isConnected;

            public final Map<String, String> variables;

            public final List<String> functions;

            @SerializedName("cc3000_patch_version")
            public final String version;

            @SerializedName("product_id")
            public final int productId;

            @SerializedName("device_needs_update")
            public final boolean requiresUpdate;

            CompleteDevice(String deviceId, String name, boolean isConnected,
                           Map<String, String> variables, List<String> functions, String version,
                           int productId, boolean requiresUpdate) {
                this.deviceId = deviceId;
                this.name = name;
                this.isConnected = isConnected;
                this.variables = variables;
                this.functions = functions;
                this.version = version;
                this.productId = productId;
                this.requiresUpdate = requiresUpdate;
            }
        }

    }


    public static class TokenResponse {

        public final String token;

        public TokenResponse(String token) {
            this.token = token;
        }
    }


    public static class CallFunctionResponse {

        @SerializedName("id")
        public final String deviceId;

        @SerializedName("name")
        public final String deviceName;

        public final boolean connected;

        @SerializedName("return_value")
        public final int returnValue;

        public CallFunctionResponse(String deviceId, String deviceName, boolean connected,
                                    int returnValue) {
            this.deviceId = deviceId;
            this.deviceName = deviceName;
            this.connected = connected;
            this.returnValue = returnValue;
        }
    }


    public static class LogInResponse {

        @SerializedName("expires_in")
        public final long expiresInSeconds;

        @SerializedName("access_token")
        public final String accessToken;

        @SerializedName("token_type")
        public final String tokenType;

        public LogInResponse(long expiresInSeconds, String accessToken, String tokenType) {
            this.expiresInSeconds = expiresInSeconds;
            this.accessToken = accessToken;
            this.tokenType = tokenType;
        }
    }


    public static class SimpleResponse {

        public final boolean ok;
        public final String error;

        public SimpleResponse(boolean ok, String error) {
            this.ok = ok;
            this.error = error;
        }

        @Override
        public String toString() {
            return "SimpleResponse [ok=" + ok + ", error=" + error + "]";
        }
    }


    public static class ClaimCodeResponse {

        @SerializedName("claim_code")
        public final String claimCode;

        @SerializedName("device_ids")
        public final String[] deviceIds;

        public ClaimCodeResponse(String claimCode, String[] deviceIds) {
            this.claimCode = claimCode;
            this.deviceIds = deviceIds;
        }
    }


    public abstract static class ReadVariableResponse<T> {

        @SerializedName("cmd")
        public final String commandName;

        @SerializedName("name")
        public final String variableName;

        public final T result;

        public final Models.CoreInfo coreInfo;

        public ReadVariableResponse(String commandName, String variableName,
                                    Models.CoreInfo coreInfo, T result) {
            this.commandName = commandName;
            this.variableName = variableName;
            this.result = result;
            this.coreInfo = coreInfo;
        }
    }


    public static class ReadIntVariableResponse extends ReadVariableResponse<Integer> {

        public ReadIntVariableResponse(String commandName, String variableName, CoreInfo coreInfo,
                                       Integer result) {
            super(commandName, variableName, coreInfo, result);
        }
    }


    public static class ReadDoubleVariableResponse extends ReadVariableResponse<Double> {

        public ReadDoubleVariableResponse(String commandName, String variableName, CoreInfo coreInfo,
                                       Double result) {
            super(commandName, variableName, coreInfo, result);
        }
    }


    public static class ReadStringVariableResponse extends ReadVariableResponse<String> {

        public ReadStringVariableResponse(String commandName, String variableName, CoreInfo coreInfo,
                                          String result) {
            super(commandName, variableName, coreInfo, result);
        }
    }


    public static class ReadObjectVariableResponse extends ReadVariableResponse<Object> {

        public ReadObjectVariableResponse(String commandName, String variableName, CoreInfo coreInfo,
                                       Object result) {
            super(commandName, variableName, coreInfo, result);
        }
    }

}
