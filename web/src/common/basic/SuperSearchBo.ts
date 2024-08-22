// @ts-nocheck
export class SuperSearchBo {

    public id?: number;
    public idList?: Array<number>;
    public page?: number = 0;
    public limit?: number = 50;


    constructor(page?: number, limit?: number) {
        this.page = page ?? 1;
        this.limit = limit ?? 50;
    }
}