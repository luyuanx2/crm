package com.yy.crm.service.service.impl;

/**
 * @author luyuanyuan on 2018/2/3.
 */
public class Demo {

//    @Autowired
//    private CountryMapper countryMapper;
//
//    public List<Country> getAll(Country country) {
//        if (country.getPage() != null && country.getRows() != null) {
//            PageHelper.startPage(country.getPage(), country.getRows());
//        }
//        Example example = new Example(Country.class);
//        Example.Criteria criteria = example.createCriteria();
//        if (country.getCountryname() != null && country.getCountryname().length() > 0) {
//            criteria.andLike("countryname", "%" + country.getCountryname() + "%");
//        }
//        if (country.getCountrycode() != null && country.getCountrycode().length() > 0) {
//            criteria.andLike("countrycode", "%" + country.getCountrycode() + "%");
//        }
//        return countryMapper.selectByExample(example);
//    }
//
//    public List<Country> getAllByWeekend(Country country) {
//        if (country.getPage() != null && country.getRows() != null) {
//            PageHelper.startPage(country.getPage(), country.getRows());
//        }
//        Weekend<Country> weekend = Weekend.of(Country.class);
//        WeekendCriteria<Country, Object> criteria = weekend.weekendCriteria();
//        if (country.getCountryname() != null && country.getCountryname().length() > 0) {
//            criteria.andLike(Country::getCountryname, "%" + country.getCountryname() + "%");
//        }
//        if (country.getCountrycode() != null && country.getCountrycode().length() > 0) {
//            criteria.andLike(Country::getCountrycode, "%" + country.getCountrycode() + "%");
//        }
//        return countryMapper.selectByExample(weekend);
//    }
//
//    public Country getById(Integer id) {
//        return countryMapper.selectByPrimaryKey(id);
//    }
//
//    public void deleteById(Integer id) {
//        countryMapper.deleteByPrimaryKey(id);
//    }
//
//    public void save(Country country) {
//        if (country.getId() != null) {
//            countryMapper.updateByPrimaryKey(country);
//        } else {
//            countryMapper.insert(country);
//        }
//    }


    //第六种，ISelect 接口方式
//jdk6,7用法，创建接口
//        Page<Country> page = PageHelper.startPage(1, 10).doSelectPage(new ISelect() {
//            @Override
//            public void doSelect() {
//                countryMapper.selectGroupBy();
//            }
//        });
//jdk8 lambda用法
//        Page<Country> page = PageHelper.startPage(1, 10).doSelectPage(()-> countryMapper.selectGroupBy());
//
////也可以直接返回PageInfo，注意doSelectPageInfo方法和doSelectPage
//        pageInfo = PageHelper.startPage(1, 10).doSelectPageInfo(new ISelect() {
//            @Override
//            public void doSelect() {
//                countryMapper.selectGroupBy();
//            }
//        });
////对应的lambda用法
//        pageInfo = PageHelper.startPage(1, 10).doSelectPageInfo(() -> countryMapper.selectGroupBy());
//
////count查询，返回一个查询语句的count数
//        long total = PageHelper.count(new ISelect() {
//            @Override
//            public void doSelect() {
//                countryMapper.selectLike(country);
//            }
//        });
////lambda
//        total = PageHelper.count(()->countryMapper.selectLike(country));
}
