/**
 * Copyright (C) 2012 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.dashboard.kpi;

import org.jboss.dashboard.provider.DataProviderManager;
import org.jboss.dashboard.database.hibernate.HibernateTxFragment;

import java.util.Collections;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.FlushMode;
import org.hibernate.Query;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class KPIManagerImpl implements KPIManager {

    /** Logger */
    private transient static Log log = LogFactory.getLog(KPIManagerImpl.class);

    @Inject
    protected DataProviderManager dataProviderManager;

    public KPI createKPI() {
        return new KPIImpl();
    }

    public Set getAllKPIs() throws Exception {
        final Set results = new HashSet();
        new HibernateTxFragment() {
        protected void txFragment(Session session) throws Exception {
            FlushMode flushMode = session.getFlushMode();
            session.setFlushMode(FlushMode.COMMIT);
            Query query = session.createQuery("from " + KPIImpl.class.getName() + " order by id");
            query.setCacheable(true);
            results.addAll(query.list());
            session.setFlushMode(flushMode);
        }}.execute();
        return results;
    }

    public KPI getKPIById(final Long id) throws Exception {
        final List results = new ArrayList();
        new HibernateTxFragment() {
        protected void txFragment(Session session) throws Exception {
            FlushMode flushMode = session.getFlushMode();
            session.setFlushMode(FlushMode.COMMIT);

            StringBuffer sql = new StringBuffer();
            sql.append("from ").append(KPIImpl.class.getName()).append(" as instance ");
            sql.append("where instance.id = :id");

            Query query = session.createQuery(sql.toString());
            if (id != null) query.setLong("id", id.longValue());
            query.setCacheable(true);
            results.addAll(query.list());
            session.setFlushMode(flushMode);
        }}.execute();
        if (results != null && results.size() > 0) return (KPIImpl) results.get(0);
        else log.debug("KPI with id =" + id + " does not exist.");
        return null;
    }

    public KPI getKPIByCode(final String code) throws Exception {
        final List results = new ArrayList();
        new HibernateTxFragment() {
        protected void txFragment(Session session) throws Exception {
            FlushMode flushMode = session.getFlushMode();
            session.setFlushMode(FlushMode.COMMIT);

            StringBuffer sql = new StringBuffer();
            sql.append("from ").append(KPIImpl.class.getName()).append(" as instance ");
            sql.append("where instance.code = :code");

            Query query = session.createQuery(sql.toString());
            if (code != null) query.setString("code", code);
            query.setCacheable(true);
            results.addAll(query.list());
            session.setFlushMode(flushMode);
        }}.execute();
        if (results != null && results.size() > 0) return (KPIImpl) results.get(0);
        else log.debug("KPI with code=" + code + " does not exist.");
        return null;
    }

    public void sortKPIsByDescription(List<KPI> list, boolean ascending) {
        KPIComparator comp = new KPIComparatorImpl();
        comp.addSortCriteria(KPIComparator.CRITERIA_DESCRIPTION, ascending ? KPIComparator.ORDER_ASCENDING : KPIComparator.ORDER_DESCENDING);
        Collections.sort(list, comp);
    }
}
