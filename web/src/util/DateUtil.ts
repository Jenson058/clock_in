// @ts-nocheck

export class DateUtil{
    public static getYear(date:Date){
        return date.getFullYear()
    }
    public static getMouth(date:Date){
        return date.getMonth() + 1
    }
    public static getDayAtMouth(date:Date){
        return date.getDay()
    }

    public static fromData(date:Date){
        return `${this.getYear(date)}-${this.getMouth(date)}`
    }
}