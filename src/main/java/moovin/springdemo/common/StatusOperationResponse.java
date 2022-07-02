package moovin.springdemo.common;

import java.util.Arrays;

public enum StatusOperationResponse {
    SUCCESS("SUCCESS", "200", "SUCCESS", true),
    ERROR_INTERNAL("ERRORINTERNAL", "500", "Si se llega a presentar este error por favor comunicarse con Moovin ya que son causas no controladas", true),
    //errortes de estimacion
    //EE
    ERROR_LIST_PRODUCT("ERRORLISTPRODUCT", "EE001", "La lista de productos a entregar se encuentra vacía", false),
    ERROR_WEIGHT("ERRORWEIGHT", "EE002", "Si uno de los productos sobrepasa el peso permitido para transportar el cual es de 45kg", false),
    ERROR_SIZE("ERRORSIZE", "EE003", "Si el paquete sobrepasa las dimensiones permitidas para ser transportado, este representa el paquete de mayor dimensión", false),
    //errores de orden lista para recoger
    //EP
    ERROR_PARAMETERS_CHANGE("ERRORPARAMETERSCHANGE", "EP001", "Cuando se intenta realizar un cambio de parámetros, pero el mismo no es permitido", false),
    PROFILE_IS_BLOCKED("PROFILEISBLOCKED", "EP002", "Si se llega a presentar este estado significa que su cuenta esta bloqueada por facturas pendientes y no podrá solicitar entregas de paquetes hasta que sean canceladas", false),
    ERROR_NOT_PERMISSION("ERRORNOTPERMISSION", "EP003", "No se tiene los permisos para ejecutar la acción sobre el paquete. Se presenta en caso de ejecutar la solicitud sobre un paquete que no pertenece al perfil", false),
    //errores de orden
    //EO
    PERMISSION_DENIED("PERMISSIONDENIED", "EO001", "Cuando no cuenta con los permisos para ejecutar la acción de eliminar un paquete. Nota: Se puede presentar por motivos de permiso o que no sea el dueño del paquete", false),
    DUPLICATE("DUPLICATE", "EO002", "Se intenta registar la eliminación de un paquete que ya se encuentra eliminado", false),
    NO_PERMIT("NOPERMIT", "EO003", "No permite eliminar, puesto que no cumple con los estados mencionados anteriormente", false),
    NO_EXIST_PACKAGE("NOEXISTPACKAGE", "EO004", "Si el paquete no existe en el sistema", false),
    DELIVERED("DELIVERED", "EO005", "El paquete ha sido entregado", false),
    UNDELIVERED("UNDELIVERED", "EO006", "El paquete no se ha entregado", false),
    ERROR_ESTIMATION("ERRORESTIMATION", "EO007", "Error con el número de estimación que se intenta solcitar", false),
    ERROR_LENGTH("ERRORLENGTH", "EO008", " Error con el largo de la descripción de un producto, supera los 256 caracteres", false),
    ERROR_LENGTH_NOTE("ERRORLENGTHNOTE", "EO009", "Supera el largo permitido para una nota de 256 caracteres", false),
    PENDING_INFO("PENDINGINFO", "EO010", "El usuario dueño de la cuenta tiene información pendiente", false),
    NO_EXIST_PAYMENT_METHOD("NOEXISTPAYMENTMETHOD", "EO011", "No cuenta con un método de pago registrado al perfil ya sea una tarjeta, billetara o crédito para realizar la solicitud", false),
    ERROR_PAYMENT_TYPE("ERRORPAYMENTTYPE", "EO012", "El método de cobro especificado no es válido", false),
    ERROR_PAYMENT_NOT_AVAILABLE("ERRORPAYMENTNOTAVAILABLE", "EO013", "El método de cobro especificado no está habilitado", false),
    ERROR_PAYMENT_CURRENCY("ERRORPAYMENTCURRENCY", "EO014", "La moneda del cobro especificado no está habilitada", false),
    ERROR_PAYMENT_AMOUNT("ERRORPAYMENTAMOUNT", "EO015", "El monto indicado del cobro no está dentro de los valores minimumAmount y maximumAmount ", false),
    //errores de zonas
    ERROR_ZONE("ERRORZONE", "EZ001", "No se encuentra dentro de la zona de cobertura de moovin", false),
    ERROR_DANGER_ZONE("ERRORDANGERZONE", "EZ002", "El punto se encuentra en una zona peligrosa, por lo que moovin no realiza la entrega", false);
    private final String message;
    private final String code;
    private final String codeMessage;
    private final boolean result;

    StatusOperationResponse(String message, String code, String codeMessage, boolean result) {
        this.message = message;
        this.code = code;
        this.codeMessage = codeMessage;
        this.result = result;
    }

    public static StatusOperationResponse getMoovinStatusOfMessage(String moovinMessage) {
        return Arrays.stream(values()).filter(v -> v.message.equals(moovinMessage)).findFirst().orElse(null);
    }

    public String getCode() {
        return code;
    }

    public String getCodeMessage() {
        return codeMessage;
    }

    public String getMessage() {
        return message;
    }

    public boolean isResult() {
        return result;
    }
}
