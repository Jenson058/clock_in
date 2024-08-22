export default class PageVo<DATA>{
    public content?: Array<DATA>;
    public totalElements?: number;
    public totalPages?: number;

    constructor(
        content?: Array<DATA>,
        totalElements?: number,
        totalPages?: number
    ) {
        this.content = content ?? [];
        this.totalElements = totalElements ?? 0;
        this.totalPages = totalPages ?? 0;
    }
}