
export default class PageConfig {

    //当前页数
    page: number
    //当前页数量
    pageSize: number
    //总数量
    total: number


    constructor(page?: number, pageSize?: number,total?: number) {
        this.page = page ?? 1;
        this.pageSize = pageSize ?? 50;
        this.total = total ?? 0;
    }
}