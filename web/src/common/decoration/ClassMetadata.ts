import {BusMap, Key, Value} from "@/common/decoration/BusMap";

/**
 * 领域 (注解所属的大的分类) 的元数据
 */
export class ClassMetadata {

    public readonly affiliatedMetadataDomain: Key;
    /**
     * 所注解的类
     */
    public readonly annotatedClass: Key;

    public readonly metadataKey: Key;
    public readonly metadataValue: Value;

    constructor(affiliatedMetadataDomain: Key, annotatedClass: Key, metadataKey: Key, metadataValue: Value) {
        this.affiliatedMetadataDomain = affiliatedMetadataDomain;
        this.annotatedClass = annotatedClass;
        this.metadataKey = metadataKey;
        this.metadataValue = metadataValue;
    }

}

export class ClassMetadataManager {
    /**
     * [Key, Key] => [annotatedClass, metadataKey]
     * @private
     */
    public readonly domainMetadataList: BusMap<[Key, Key, Key], ClassMetadata>;

    constructor() {
        this.domainMetadataList = new BusMap<[Key, Key, Key], ClassMetadata>();
    }
    /**
     * todo 在模糊搜索的基础之上, 扩充一系列常用的精确搜索函数
     * 模糊搜索
     * @param affiliatedMetadataDomain
     * @param annotatedClass
     * @param metadataKey
     */
    public findAll(affiliatedMetadataDomain?: Key, annotatedClass?: Key, metadataKey?: Key): Array<ClassMetadata> {
        return this.domainMetadataList.filter(
            ([currAffiliatedMetadataDomain, currAnnotatedClass, currMetadataKey]) => {
                return (affiliatedMetadataDomain ? currAffiliatedMetadataDomain == affiliatedMetadataDomain : true)
                    && (annotatedClass ? currAnnotatedClass == annotatedClass : true)
                    && (metadataKey ? currMetadataKey == metadataKey : true)
            }
        )
    }

    /**
     * 精确查找
     * @param affiliatedMetadataDomain
     * @param annotatedClass
     * @param metadataKey
     */
    public find(affiliatedMetadataDomain: Key, annotatedClass: Key, metadataKey: Key): ClassMetadata | undefined {
        let result = this.findAll(affiliatedMetadataDomain, annotatedClass, metadataKey);
        if (result.length == 0) return undefined;
        return result[0];
    }

    public add(affiliatedMetadataDomain: Key, annotatedClass: Key, metadataKey: Key, metadataValue: Value): void {
        this.domainMetadataList.set(
            [affiliatedMetadataDomain, annotatedClass, metadataKey],
            new ClassMetadata(affiliatedMetadataDomain, annotatedClass, metadataKey, metadataValue)
        );
    }
}