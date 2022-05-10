package org.bougainvilleas.base.grace.chapter11;

/**
 * 139.大胆采用开源工具
 * 开源工具包确实会对我们的项目有非常大的帮助，
 * 比如提升代码质量，减少Bug产生，降低工作量等，
 * 但一旦项目中的工具杂乱无章时就会产生依赖的无序性，这会导致代码中隐藏着炸弹，不知何时就会突然引爆
 * 选择开源工具和框架时要遵循一定的原则：
 * 1）普适性原则
 *      选择一个工具或框架就必须考虑项目成员的整体技术水平，
 *      不能有太大的跨度或跳跃性，要确保大部分项目成员对工具都比较熟悉，
 *      若一个项目中的成员大部分是新员工，那么在持久层框架的选择上，使用MyBatis就比Hibernate要合适，因为MyBatis相对简单、方便；
 *      再比如在一个熟悉SSH开发的团队中，就不应该无故选择Guice作为IoC容器，除非是行政命令或为了尝鲜
 * 2）唯一性原则
 *      相同的工具只选择一个或一种，不要让多种相同或相似职能的工具共存。
 *      例如：集合工具
 *          可以使用Apache Commons的collections包，
 *          也可以使用Google Guava的Collections工具包，
 *          但是在项目开发前就应该确认下来，不能让两者共存
 * 3）“大树纳凉”原则
 *      在选择工具包时得寻找比较有名的开源组织，
 *      比如Apache、Spring、opensymphony（虽然已经关闭，但它曾经是那么耀眼、辉煌）、Google等，
 *      这些开源组织
 *          一则具有固定的开发和运作风格，
 *          二则具有广阔的使用人群（很多情况下，我们不会是第一个发现Bug的人），
 *      在这样的大树下，我们才有时间和精力纳凉，而不会把大好的时间消耗在排查Bug上
 * 4）精而专原则
 *      选择的工具包应该是精而专的，而不是广而多的，
 *      比如
 *          虽然Spring框架提供了Utils工具包，但在一般情况下不要使用它，因为它不专，Utils工具包只是Spring框架中的一个附加功能而已，
 *          要用就用ApacheCommons的BeanUtils、Lang等工具包
 * 5）高热度原则
 *      一个开源项目的热度越高，更新得就越频繁，使用的人群就越广，Bug的曝光率就越快，修复效率也就越高，
 *      这对我们项目的稳定性来说是非常重要的。
 *      有很多开源项目可能已经很长时间没有更新了，或者是已经非常成熟了，或者是濒于关闭了，
 *      这我们不能要求太高，毕竟开源项目已经共享出了他人的精力和智力，
 *      我们在享受他人提供的成果的同时，也应该珍惜他人的劳动，
 *      最低的标准是不要诋毁开源项目
 *
 * 对于开源工具，我们应该大胆采用，仔细筛选，
 * 如果确实所有的开源工具都无法满足我们的需求，
 * 那就自己开发一个开源项目，为千千万万的Java人服务，也为Java的生态系统贡献自己的力量
 */
public class Fj {}