
// Walidacja tworzenia zgłoszeń o takiej samej wartości

import com.atlassian.jira.component.ComponentAccessor
import com.atlassian.jira.issue.CustomFieldManager
import com.atlassian.jira.issue.fields.CustomField
import com.atlassian.jira.issue.search.SearchProvider
import com.atlassian.jira.user.ApplicationUser
import com.atlassian.jira.web.bean.PagerFilter

def customField = ComponentAccessor.getCustomFieldManager().getCustomFieldObjectByName("Nazwa pola własnego")
def customFieldValue = customField.getValue(issue)

def searchProvider = ComponentAccessor.getComponent(SearchProvider)
def query = "\"Nazwa pola własnego\" ~ \"" + customFieldValue + "\""
def results = searchProvider.search(query, ComponentAccessor.getJiraAuthenticationContext().getLoggedInUser(), PagerFilter.getUnlimitedFilter())

if (results.total >= 1) {
    return "Zgłoszenie z taką wartością pola własnego już istnieje."
} else {
    return "Zgłoszenie nie istnieje."
}