export type Key = string
export type Value = any

export class BusMap<B extends Array<string | number>, V> {
    private readonly busMap: Map<string, V>
    private static separator: string = "::";
    constructor() {
        this.busMap = new Map<string, V>();
    }

    private getRealKey(busKey: B): string {
        let realKey: string[] = [];
        for (let key of busKey) {
            realKey.push(key.toString())
        }
        return realKey.join(BusMap.separator);
    }

    public getKeys(): string[] {
        let keys = []
        for (let key of this.busMap.keys())
            keys.push(key)
        return keys;
    }

    private parseRealKek(key: string): B {
        return key.split(BusMap.separator) as B;
    }

    public set(busKey: B, value: V) {
        this.busMap.set(this.getRealKey(busKey), value)
    }

    public get(busKey: B): V | undefined {
        return this.busMap.get(this.getRealKey(busKey))
    }

    public has(busKey: B): boolean {
        return this.busMap.has(this.getRealKey(busKey))
    }

    /**
     * 对于 BusMap 自定义的 filter 函数, 如果没找到会默认返回一个空的数组
     * @param predicate
     */
    public filter(predicate: (busKeys: B, value: V, realKey: string, busMap: Map<string, V>) => boolean) {
        let result: Array<V> = new Array<V>();
        for (let busMapElement of this.busMap) {
            if (predicate(this.parseRealKek(busMapElement[0]), busMapElement[1], busMapElement[0], this.busMap)) {
                result.push(busMapElement[1]);
            }
        }
        return result;
    }
}