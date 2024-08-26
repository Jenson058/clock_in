export enum DateEnum {
    Y,
    M,
    D,
    Y_M,
    Y_M_D,
    M_D

}

export function formatDate(date?: Date, fatter?: DateEnum) {
    if (!date){
        return ""
    }
    switch (fatter) {
        case DateEnum.Y:
            return '' + date.getFullYear();
        case DateEnum.M:
            return '' + (date.getMonth() + 1);
        case DateEnum.D:
            return '' + date.getDate();
        case DateEnum.Y_M:
            return '' + date.getFullYear() + '-' + (date.getMonth() + 1);
        case DateEnum.Y_M_D:
            return '' + date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate();
        case DateEnum.M_D:
            return '' + date.getMonth() + '-' + date.getDate();
        default:
            return '' + date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate();
    }
}
